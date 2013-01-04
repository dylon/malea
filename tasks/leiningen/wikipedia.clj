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

(ns leiningen.wikipedia 
  (:use [clojure.tools.cli :only [cli]])
  (:require [leiningen.core.eval :as lein.eval]))

(defn- train! [project model]
  (if (= model "corpus")
    (lein.eval/eval-in-project project
      `(do
         (require 'malea.wikipedia
                  'malea.database)
         (malea.database/establish-malea-db-connection!)
         (with-open [xml-resource#
                      (clojure.java.io/input-stream
                        (clojure.java.io/resource
                          "wikipedia/enwiki-latest-pages-articles.xml"))]
         (malea.wikipedia/insert-pages-from-xml xml-resource#)))))
  (binding [*out* *err*]
    (println (str "The model you specified is \""model"\". The only model supported is \"corpus\""))
    (System/exit 1)))

(defn- clean! [project table]
  (if (= table "grams")
    (lein.eval/eval-in-project project
      `(do
         (require 'malea.database
                  'korma.core)
         (malea.database/establish-malea-db-connection!)
         (korma.core/exec-raw [(str
           "DELETE FROM grams "
           "WHERE NOT EXISTS ("
             "SELECT true "
             "FROM trigrams "
             "WHERE trigrams.gram_1_id = grams.id "
                "OR trigrams.gram_2_id = grams.id "
                "OR trigrams.gram_3_id = grams.id"
           ") "
           "AND NOT EXISTS ("
             "SELECT true "
             "FROM bigrams "
             "WHERE bigrams.gram_1_id = grams.id "
                "OR bigrams.gram_2_id = grams.id"
           ") "
           "AND NOT EXISTS ("
             "SELECT true "
             "FROM unigrams "
             "WHERE unigrams.gram_1_id = grams.id"
           ")")])))
    (binding [*out* *err*]
      (println (str "The table you specified is \""table"\". The only table supported is \"grams\""))
      (System/exit 1))))

(defn- drop-indices! [])

(defn- restore-indices! [])

(defn- print-stats [model]
  (if (= model "invalid")
    '()
    (binding [*out* *err*]
      (println (str "You specified \""model"\", but the only supported model is \"invalid\""))
      (System/exit 1))))

(defn- parse-args [args]
  (cli args
    ["-t" "--train" "Trains a model of the type specified."]
    ["-c" "--clean" "Cleans the specified table."]
    ["-d" "--drop-indices" "Drop most of the indices to speed up insertions." :flag true]
    ["-r" "--restore-indices" "Restore any dropped indices for data integrity." :flag true]
    ["-s" "--stats" "Print statistics of the given type."]))

(defn wikipedia [project & args]
  (let [[options args banner] (parse-args args)]
    (doseq [[command arguments] options]
      (case command 
        :drop-indices (when arguments (drop-indices!))
        :restore-indices (when arguments (restore-indices!))
        :train (when arguments (train! project arguments))
        :clean (when arguments (clean! project arguments))
        :stats (when arguments (print-stats arguments))))))

