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

(defn- exec-sql! [project path]
  (lein.eval/eval-in-project project
    `(do
       (require 'malea.database
                'clojure.java.jdbc)
       (malea.database/establish-malea-db-connection!)
       (let [sql# (slurp (clojure.java.io/input-stream ~path))]
         (clojure.java.jdbc/with-connection
           (malea.database/malea-db-connection)
           (clojure.java.jdbc/do-prepared sql#))))))

(defn- drop-constraints! [project]
  (exec-sql! project "src/postgresql/tasks/drop_constraints.pgsql"))

(defn- clean-data! [project]
  (exec-sql! project "src/postgresql/tasks/clean_data.pgsql"))

(defn- restore-constraints! [project]
  (exec-sql! project "src/postgresql/tasks/restore_constraints.pgsql"))

(defn- print-stats [project model]
  (if (= model "invalid")
    '()
    (binding [*out* *err*]
      (println (str "You specified \""model"\", but the only supported model is \"invalid\""))
      (System/exit 1))))

(defn- parse-args [args]
  (cli args
    ["-t" "--train" "Trains a model of the type specified."]
    ["-d" "--drop-constraints" "Drops the database constraints for bulk importation." :flag true]
    ["-c" "--clean-data" "Cleans the database. If this is needed, there is a bug." :flag true]
    ["-r" "--restore-constraints" "Restores the database constraints." :flag true]
    ["-s" "--print-stats" "Print statistics of the given type."]))

(defn wikipedia [project & args]
  (let [[options args banner] (parse-args args)]
    (doseq [[command arguments] options]
      (case command
        :train (when arguments (train! project arguments))
        :drop-constraints (when arguments (drop-constraints! project))
        :clean-data (when arguments (clean-data! project))
        :restore-constraints (when arguments (restore-constraints! project))
        :print-stats (when arguments (print-stats project arguments))))))

