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
        clojure.pprint
        clojure.data)
  (:require [malea.nlp :as nlp]
            [clojure.data.xml :as xml])
  (:import org.postgresql.util.PSQLException))

(def ^:private ^:const BATCH-SIZE 1000)

(defentity pages)
(defentity grams)
(defentity trigrams)
(defentity bigrams)
(defentity unigrams)

(defn insert-grams-if-not-exist! [records]
  "Inserts new grams into the database.  Each gram should be a string."
  (doseq [record records]
    (loop [batch (take BATCH-SIZE records)
           records (drop BATCH-SIZE records)]
      (when-not (empty? batch)
        (exec-raw
          [(str
             "SELECT insert_grams_if_not_exist(ARRAY["
               (clojure.string/join ","
                 (take (count batch) (repeat \?))) "])")
           (map #(:value %) batch)] :results)))))

(defn insert-trigrams! [page-id records]
  "Inserts new trigrams into the database."
  (loop [batch (take BATCH-SIZE records)
         records (drop BATCH-SIZE records)]
    (when-not (empty? batch)
      (insert trigrams
        (values batch))
      (recur (take BATCH-SIZE records)
             (drop BATCH-SIZE records)))))

(defn insert-bigrams! [page-id records]
  "Inserts new bigrams into the database."
  (loop [batch (take BATCH-SIZE records)
         records (drop BATCH-SIZE records)]
    (when-not (empty? batch)
      (insert bigrams
        (values batch))
      (recur (take BATCH-SIZE records)
             (drop BATCH-SIZE records)))))

(defn insert-unigrams! [page-id records]
  "Inserts new unigrams into the database."
  (loop [batch (take BATCH-SIZE records)
         records (drop BATCH-SIZE records)]
    (when-not (empty? batch)
      (insert unigrams
        (values batch))
      (recur (take BATCH-SIZE records)
             (drop BATCH-SIZE records)))))

(defn gram-map [values]
  "Bijection of gram-text onto gram-id from the database."
  (->> (select grams
          (fields :id :value)
          (where {:value [in values]}))
    (reduce
      #(assoc %1 (:value %2) (:id %2)) {})))

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

;; TODO: Strip all HTML tags from the documents.  This may be able to go into
;; `malea.nlp/preprocess`.

;; The original XML-parsing algorithm came from here:
;; http://stackoverflow.com/a/9993325/206543

(defn n-gram [page-id frequency records attributes]
  "Constructs a new n-gram record, and appends it to `records`.

  1. `page-id` Identifier of the corresponding page.
  2. `frequency` Mapping of n-grams to their frequency in the page.
  3. `records` Accumulator of n-gram records.
  4. `attributes` Sequential identifiers of grams within this record."

  (loop [index 0
         n-gram {}]
    (if-not (< index (count attributes))
      (conj records
        (assoc n-gram
          :page_id page-id
          :frequency (frequency attributes)))
      (recur
        (inc index)
        (assoc n-gram
          (keyword (str "gram_" (inc index) "_id"))
          (nth attributes index))))))

(defn insert-or-update-page! [page]
  "If the page does not exist, it is created; otherwise, it is updated."
  (first
    (exec-raw
      ["SELECT insert_or_update_page(?::integer,?::integer,?::varchar,?::varchar,?::text)"
       (map page [:id :revision :title :redirect :text])] :results)))

(defn- insert-page-from-xml! [page]
  (let [{page-id :id, page-text :text} page
        preprocessed-text (nlp/preprocess page-text)
        sentences (nlp/get-sentences preprocessed-text)
        tokens (map nlp/tokenize sentences)
        distinct-tokens (distinct (flatten tokens))
        page-task (future (insert-or-update-page! page))
        gram-task
          (future
            (insert-grams-if-not-exist!
              (map #(hash-map :value %) distinct-tokens)))]

    @gram-task

    ;; TODO: Refactor to clean these up!!!
    (when-let [retval @page-task]
      (let [gram-map (gram-map distinct-tokens)
            trigram-task
              (future
                (let [trigrams (apply concat (map nlp/trigrams tokens))
                      trigram-ids (map #(vec (map gram-map %)) trigrams)
                      trigram-frequency (nlp/frequency trigram-ids)
                      trigram-records
                        (reduce
                          (partial n-gram page-id trigram-frequency)
                          [] (distinct trigram-ids))]
                  
                  ;(try
                    ;(insert-trigrams! page-id trigram-records)
                    ;(catch Exception exception
                      ;(newline)
                      ;(prn (clojure.string/join "" (repeat 100 \=)))
                      ;(prn "::: retval")
                      ;(newline)
                      ;(pprint retval)
                      ;(newline)
                      ;(prn (clojure.string/join "" (repeat 100 \=)))
                      ;(prn "::: page")
                      ;(newline)
                      ;(pprint page)
                      ;(newline)
                      ;(prn (clojure.string/join "" (repeat 100 \=)))
                      ;(prn "::: Page from Database")
                      ;(newline)
                      ;(pprint (select pages (where (= :id page-id))))
                      ;(newline)
                      ;(prn (clojure.string/join "" (repeat 100 \=)))
                      ;(prn "Trigrams")
                      ;(newline)
                      ;(pprint trigram-records)
                      ;(newline)
                      ;(prn (clojure.string/join "" (repeat 100 \=)))
                      ;(throw exception)))
                  
                  (insert-trigrams! page-id trigram-records)))
            bigram-task
              (future
                (let [bigrams  (apply concat (map nlp/bigrams tokens))
                      bigram-ids  (map #(vec (map gram-map %)) bigrams)
                      bigram-frequency (nlp/frequency bigram-ids)
                      bigram-records
                        (reduce
                          (partial n-gram page-id bigram-frequency)
                          [] (distinct bigram-ids))]
                  (insert-bigrams! page-id bigram-records)))
            unigram-task
              (future
                (let [unigrams (apply concat (map nlp/unigrams tokens))
                      unigram-ids (map #(vec (map gram-map %)) unigrams)
                      unigram-frequency (nlp/frequency unigram-ids)
                      unigram-records
                        (reduce
                          (partial n-gram page-id unigram-frequency)
                          [] (distinct unigram-ids))]
                  (insert-unigrams! page-id unigram-records)))]
        @trigram-task
        @bigram-task
        @unigram-task))))

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
  (dorun (->> (:content (xml/parse xml-input-stream :coalescing false))
    (filter #(= :page (:tag %)))
    (pmap (partial process-page-element! {})))))

