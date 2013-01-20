;; Copyright (c) 2012 Dylon Edwards
;;
;; Permission is hereby granted, free of charge, to any person obtaining a copy
;; of this software and associated documentation files (the "Software"), to deal
;; in the Software without restriction, including without limitation the rights
;; to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
;; copies of the Software, and to permit persons to whom the Software is
;; furnished to do so, subject to the following conditions:
;;
;; The above copyright notice and this permission notice shall be included in
;; all copies or substantial portions of the Software.
;;
;; THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;; IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;; FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;; AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;; LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;; OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
;; SOFTWARE.

(ns malea.wikipedia
  (:use korma.core
        clojure.data
        clojure.pprint)
  (:require [malea.nlp :as nlp]
            [malea.database :as db]
            [clojure.data.xml :as xml]
            [clojure.java.jdbc :as jdbc]
            [korma.db])
  (:import java.util.concurrent.ArrayBlockingQueue
           java.util.concurrent.TimeUnit
           org.postgresql.util.PSQLException
           org.stringtemplate.v4.STGroupFile))

(def ^:private ^:const BATCH-SIZE 250)

(defentity pages)
(defentity grams)
(defentity n_grams)
(defentity trigrams)   ;-> view, not a table
(defentity bigrams)    ;-> view, not a table
(defentity unigrams)   ;-> view, not a table
(defentity quadgrams)  ;-> view, not a table

(def ^:private wikipedia-stg
  (delay
    (let [st-group
            (STGroupFile.
              "src/stringtemplate/postgresql/malea/wikipedia.stg")]

      ;; pre-compile the templates for thread safety
      (dorun
        (map
          #(.getInstanceOf st-group %)
          (.getTemplateNames st-group)))

      st-group)))

(defn- wikipedia-st [template-name]
  "New StringTemplate instance of the StringTemplateGroup wikipedia-stg"
  (.getInstanceOf @wikipedia-stg template-name))

(defn insert-grams-if-not-exist! [records]
  "Inserts new grams into the database.  Each gram should be a string."
  (apply merge
    (pmap
      (fn [batch]
        (let [template (wikipedia-st "insert_grams_if_not_exist")]
          (.add template "range" (range (count batch)))
          (reduce #(assoc %1 (:value %2) (:id %2)) {}
            (exec-raw [(.render template) batch] :results))))
      (partition-all BATCH-SIZE records))))

(defn reserve-n-grams! [n-gram-count]
  "Reserves the `n-gram-count` identifiers within the `n_grams` table"
  (-> (exec-raw ["SELECT reserve_n_grams(?::integer)" [n-gram-count]] :results)
    (first)
    (get :reserve_n_grams)
    (.getArray)))

(defn unwrap-n-grams
  "Accepts an n-gram trie and unwraps it to a list of n-gram records, which may
  be inserted into the `n_grams` table."
  ([gram-trie page-id]
   (unwrap-n-grams gram-trie page-id '()))
  ([gram-trie page-id unwrapped-n-grams]
   (if (empty? gram-trie) unwrapped-n-grams
     (let [[gram-id record] (first gram-trie)
           gram-trie (rest gram-trie)
           unwrapped-n-grams
            (conj unwrapped-n-grams
              (assoc
                (select-keys record [:id :parent_id :frequency])
                :gram_id gram-id
                :page_id page-id))
           unwrapped-n-grams
            (concat unwrapped-n-grams
              (unwrap-n-grams (:children record) nil '()))]
       (recur gram-trie page-id unwrapped-n-grams)))))

(defn set-id-and-parent-id
  "Walks the `gram-trie` and associates with each of its nodes an `id` attribute
  and `parent-id` attribute (if it has a parent).  The function accepts two
  parameters: `gram-trie`, which is a trie of n-grams; and `n-gram-ids`, which
  is a list of unique identifiers."
  ([gram-trie n-gram-ids]
   (first
     (set-id-and-parent-id gram-trie n-gram-ids {} nil)))
  ([gram-trie n-gram-ids gram-trie-with-ids parent-id]
   (if (empty? gram-trie) [gram-trie-with-ids n-gram-ids]
     (let [[gram data] (first gram-trie)
           gram-trie (rest gram-trie)
           n-gram-id (first n-gram-ids)
           n-gram-ids (rest n-gram-ids)
           [children-n-gram-trie n-gram-ids]
             (set-id-and-parent-id
               (:children data) n-gram-ids {} n-gram-id)
           gram-trie-with-ids
             (assoc gram-trie-with-ids gram
               (assoc data
                 :id n-gram-id
                 :parent_id parent-id
                 :children children-n-gram-trie))]
       (recur gram-trie n-gram-ids gram-trie-with-ids parent-id)))))

(defn insert-n-grams! [gram-map page-id gram-trie]
  "Inserts new n-grams into the database.  `gram-trie` should be structured as
  follows:

  (let [gram-trie {
    \"unigram-1\" {
      :frequency 12
      :children {
        \"bigram-1\" {
          :frequency 23
          :children {
            \"trigram-1\" {
              :frequency 12}
            \"trigram-2\" {
              :frequency 1}}}
        \"bigram-2\" {
          :frequency 58
          :children {
            \"trigram-3\" {
              :frequency 278
              :children {
                \"quadgram-1\" {
                  :frequency 28}}}}}}}
    \"unigram-2\" {
      :frequency 98
      :children {
        \"bigram-3\" {
          :frequency 68} ;; NOTE: There are no children for this bigram!
        \"bigram-4\" {
          :frequency 5
          :children {
            \"trigram-4\" {
              :frequency 30}}}}}}]
    (insert-n-grams! gram-map page-id gram-trie))

  where, `frequency` is the number of time the n-gram appeared in the page, and
  `children` consists of the (n+1)-grams having the n-gram as their prefix.  The
  keys are the values of the n-gram tails (e.g. if one has the bigram
  [\"one\" \"two\"], then the key would be \"two\"). Notice that each n-gram has
  a frequency but each does not have children: this structure will easily let me
  add quadgrams (four-grams) and quintgrams (five-grams), and even drop the
  trigrams (three-grams); the catch is that there must be an (n-1)-gram for
  there to be an n-gram."

  (let [n-gram-ids (reserve-n-grams! (nlp/count-n-grams gram-trie))
        gram-trie (set-id-and-parent-id gram-trie n-gram-ids)
        n-grams (unwrap-n-grams gram-trie page-id)]
    (dorun
      (map
        #(insert n_grams
          (values %))
        (partition-all BATCH-SIZE n-grams)))))

(defn trigrams-for-page [^long page-id]
  "Trigrams for the given page."
  (-> (select* trigrams)
    (where {:page_id page-id})))

(defn bigrams-for-page [^long page-id]
  "Bigrams for the given page."
  (-> (select* bigrams)
    (where {:page_id page-id})))

(defn unigrams-for-page [^long page-id]
  "Unigrams for the given page."
  (-> (select* unigrams)
    (where {:page_id page-id})))

(defn- filter-tag [tag xml]
  (filter #(= tag (:tag %)) xml))

(defn insert-or-replace-page! [page]
  "If the page does not exist, it is created; otherwise, it is replaced."
  (let [{:keys [title id redirect revision text term_sequence]} page
        statement
          "SELECT insert_or_update_page( ?::varchar(256)
                                       , ?::integer
                                       , ?::varchar(256)
                                       , ?::integer
                                       , ?::text
                                       , ?::integer[]
                                       );"
        term_sequence
          (-> (wikipedia-st "term_sequence")
            (.add "sequence" term_sequence)
            (.render))]
    (exec-raw
      [statement [title id redirect revision text term_sequence]] :results)))

(defn- insert-page-from-xml! [page]
  ;(korma.db/transaction
    (let [{page-id :id, page-text :text} page
          preprocessed-text (nlp/preprocess page-text)
          term-sequence (nlp/tokenize preprocessed-text)
          distinct-terms (set term-sequence)
          gram-map (insert-grams-if-not-exist! distinct-terms)
          term-sequence (map gram-map term-sequence)
          n-grams (nlp/extract-n-grams 4 term-sequence)
          gram-trie (reduce nlp/insert-n-gram {} n-grams)
          ;; Ignore the raw text for now -- it will consume too much space
          page (assoc (dissoc page :text) :term_sequence term-sequence)]
      (insert-or-replace-page! page)
      (insert-n-grams! gram-map page-id gram-trie)));)

(defn- page-is-valid? [page]
  (and (:id page) (:title page) (:revision page) (:text page)))

(defn- extract-attribute [element attribute]
  (first
    (:content
      (first
        (filter
          #(= attribute (:tag %))
          (:content element))))))

(defn- process-text-element! [attributes text-element]
  (let [page-text (clojure.string/join "" (:content text-element))
        attributes (assoc attributes :text page-text)]
    (when (page-is-valid? attributes)
      (insert-page-from-xml! attributes))))

(defn- process-revision-element! [attributes revision-element]
  (let [revision-id (read-string (extract-attribute revision-element :id))
        attributes (assoc attributes :revision revision-id)]

    (process-text-element! attributes
      (first
        (filter #(= :text (:tag %))
          (:content revision-element))))))

(defn- extract-redirect [page-element]
  (:title
    (:attrs
      (first
        (filter #(= :redirect (:tag %))
          (:content page-element))))))

(defn- process-page-element! [attributes page-element]
  (let [page-id (read-string (extract-attribute page-element :id))
        page-title (extract-attribute page-element :title)
        page-redirect (extract-redirect page-element)
        attributes
          (assoc attributes
            :id page-id
            :title page-title
            :redirect page-redirect)]

    (process-revision-element! attributes
      (first
        (filter #(= :revision (:tag %))
          (:content page-element))))))

(defn insert-pages-from-xml [xml-input-stream]
  (let [run? (atom true)
        number-of-threads (+ 2 (.. Runtime getRuntime availableProcessors))
        queue (ArrayBlockingQueue. (* 32 number-of-threads))
        terminators (repeat number-of-threads (promise))
        threads
          (map
            (fn [terminated]
              (doto (Thread.
                (fn []
                  (while @run?
                    (when-let [page-element (.poll queue 60 TimeUnit/SECONDS)]
                      (process-page-element! {} page-element)))
                  (deliver terminated true)))
                (.start)))
            terminators)]
    (dorun threads)
    (doseq [page-element
              (->> (:content (xml/parse xml-input-stream
                     :coalescing false
                     :namespace-aware false
                     :replacing-entity-references false
                     :supporting-external-entities false))
                (filter #(= :page (:tag %))))]
      (.put queue page-element))
    (reset! run? false)
    (doseq [terminated terminators]
      @terminated)))

