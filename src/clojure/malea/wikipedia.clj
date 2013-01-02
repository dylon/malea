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
        clojure.pprint)
  (:require [malea.nlp :as nlp]
            [clojure.data.xml :as xml]))

(defentity pages)
(defentity grams)
(defentity trigrams)
(defentity bigrams)
(defentity unigrams)

(defn insert-pages [records]
  "Inserts complete, new Wikipedia pages into the database."
  (insert pages
    (values records)))

(defn insert-grams [records]
  "Inserts new grams into the database.  Each gram should be a string."
  (doseq [record records]
    (when (empty?
      (select grams
        (aggregate (count :*) :singleton :value)
        (where {:value [= (:value record)]})
        (limit 1)))
      (insert grams 
        (values [record])))))

(defn insert-trigrams [records]
  "Inserts new trigrams into the database."
  (insert trigrams 
    (values records)))

(defn insert-bigrams [records]
  "Inserts new bigrams into the database."
  (insert bigrams
    (values records)))

(defn insert-unigrams [records]
  "Inserts new unigrams into the database."
  (insert unigrams
    (values records)))

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

(defn- insert-page-from-xml [{page-id :id, page-text :text}]
  (let [preprocessed-text (nlp/preprocess page-text)
        sentences (nlp/get-sentences preprocessed-text)
        tokens (map nlp/tokenize sentences)
        distinct-tokens (distinct (flatten tokens))
        trigrams (apply concat (map nlp/trigrams tokens))
        bigrams  (apply concat (map nlp/bigrams tokens))
        unigrams (apply concat (map nlp/unigrams tokens))]

    (insert-pages [{:id page-id, :text page-text}])
    (insert-grams (map #(hash-map :value %) distinct-tokens))
    
    (let [gram-map (gram-map distinct-tokens)
          trigram-ids (map #(vec (map gram-map %)) trigrams)
          bigram-ids  (map #(vec (map gram-map %)) bigrams)
          unigram-ids (map #(vec (map gram-map %)) unigrams)
          trigram-frequency (nlp/frequency trigram-ids)
          bigram-frequency  (nlp/frequency bigram-ids)
          unigram-frequency (nlp/frequency unigram-ids)
          trigram-records
            (reduce
              (partial n-gram page-id trigram-frequency)
              [] (distinct trigram-ids))
          bigram-records
            (reduce
              (partial n-gram page-id bigram-frequency)
              [] (distinct bigram-ids))
          unigram-records
            (reduce
              (partial n-gram page-id unigram-frequency)
              [] (distinct unigram-ids))]
      
      (insert-trigrams trigram-records)
      (insert-bigrams  bigram-records)
      (insert-unigrams unigram-records))))

(defn- process-text-element [attributes text-element]
  (let [page-text (first (:content text-element))
        attributes (assoc attributes :text page-text)]
    (insert-page-from-xml attributes)))

(defn- process-revision-element [attributes revision-element]
  (dorun (->> (:content revision-element)
    (filter #(= :text (:tag %)))
    (map (partial process-text-element attributes)))))

(defn- extract-page-id [page-element]
  (read-string
    (first
      (:content
        (first
          (filter
            #(= :id (:tag %))
            (:content page-element)))))))

(defn- process-page-element [attributes page-element]
  (let [page-id (extract-page-id page-element)
        attributes (assoc attributes :id page-id)]
    (dorun (->> (:content page-element)
      (filter #(= :revision (:tag %)))
      (map (partial process-revision-element attributes))))))

(defn insert-pages-from-xml [xml-input-stream]
  (dorun (->> (:content (xml/parse xml-input-stream :coalescing false))
    (filter #(= :page (:tag %)))
    (map (partial process-page-element {})))))

