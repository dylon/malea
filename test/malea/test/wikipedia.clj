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

(ns malea.test.wikipedia
  (:use malea.wikipedia
        [korma core db]
        midje.sweet
        clojure.pprint)
  (:require [malea.wikipedia :as wiki]
            [malea.nlp :as nlp]  
            [malea.database :as db]
            [malea.test.data :as data :only [xml-document]]))

;; Prime the database connection
(db/establish-malea-test-db-connection!)

;; NOTE: The parser will break if :text=""

(let [page-text "This is a sentence. This is another one from Dr. Insanity!"
      page-id 1
      sentences (nlp/get-sentences (nlp/preprocess page-text))
      tokens (map nlp/tokenize sentences)
      distinct-tokens (distinct (flatten tokens))
      trigrams (apply concat (map nlp/trigrams tokens))
      bigrams  (apply concat (map nlp/bigrams tokens))
      unigrams (apply concat (map nlp/unigrams tokens))]

  (against-background
    [(around :contents (transaction ?form (rollback)))
     (after :contents
        (exec-raw
          ["SELECT setval('grams_id_seq', 1);"] :results   
          ["SELECT setval('trigrams_id_seq', 1);"] :results   
          ["SELECT setval('bigrams_id_seq', 1);"] :results   
          ["SELECT setval('unigrams_id_seq', 1);"] :results))]

    (wiki/insert-pages [{:id page-id :text page-text}])
    (wiki/insert-grams (map #(hash-map :value %) distinct-tokens))

    (let [gram-map (wiki/gram-map distinct-tokens)
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

      (wiki/insert-trigrams trigram-records)
      (wiki/insert-bigrams  bigram-records)
      (wiki/insert-unigrams unigram-records)

      (fact "Trigrams for Page"
        (-> (wiki/trigrams-for-page page-id)
          (fields
            :page_id
            :gram_1_id
            :gram_2_id
            :gram_3_id
            :frequency)
          (select)) =>
            (just (set trigram-records)))
      (fact "Bigrams for Page"
        (-> (wiki/bigrams-for-page page-id)
          (fields
            :page_id
            :gram_1_id
            :gram_2_id
            :frequency)
          (select)) =>
            (just (set bigram-records)))
      (fact "Unigrams for Page"
        (-> (wiki/unigrams-for-page page-id)
          (fields
            :page_id
            :gram_1_id
            :frequency)
          (select)) =>
            (just (set unigram-records))))))

(against-background
  [(around :contents (transaction ?form (rollback)))
   (after :contents
      (exec-raw
        ["SELECT setval('grams_id_seq', 1);"] :results   
        ["SELECT setval('trigrams_id_seq', 1);"] :results   
        ["SELECT setval('bigrams_id_seq', 1);"] :results   
        ["SELECT setval('unigrams_id_seq', 1);"] :results))]
  (fact
    (wiki/insert-pages-from-xml
      (java.io.StringReader. data/xml-document)) =not=> (throws Exception)))
