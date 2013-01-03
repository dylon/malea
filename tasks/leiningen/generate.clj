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

(ns leiningen.generate
  (:use [clojure.tools.cli :only [cli]])
  (:require [leiningen.core.eval :as lein.eval])
  (:import java.util.Calendar
           java.io.File
           org.stringtemplate.v4.ST))

(def ^:private ^:const MIGRATIONS-PATH "migrations")
(def ^:private ^:const MIGRATION-TEMPLATE "src/stringtemplate/postgresql/migration.sql.stg")

(def ^:private ^:const SOURCE-PATH "src/clojure")
(def ^:private ^:const SOURCE-TEMPLATE "src/stringtemplate/clojure/source.clj.stg")

(def ^:private ^:const TEST-PATH "test")
(def ^:private ^:const TEST-TEMPLATE "src/stringtemplate/clojure/test.clj.stg")

(defn calendar-attributes []
  (let [calendar (Calendar/getInstance)]
    {"year"             (.get calendar Calendar/YEAR)
     "month"       (inc (.get calendar Calendar/MONTH))
     "day"              (.get calendar Calendar/DAY_OF_MONTH)
     "hour"             (.get calendar Calendar/HOUR_OF_DAY)
     "minute"           (.get calendar Calendar/MINUTE)
     "second"           (.get calendar Calendar/SECOND)
     "millisecond"      (.get calendar Calendar/MILLISECOND)}))

(defn author-attributes []
  {"author" "Dylon Edwards"
   "email"  "dylon.edwards@gmail.com"})

(defn shared-attributes []
  (merge (author-attributes)
         (calendar-attributes)))

(defn ->template
  ([#^String text ^java.util.Map attributes]
   (let [attributes (merge (shared-attributes) attributes)
         template (ST. text)]
     (doseq [[^String name ^Object value] attributes]
       (.add template name value))
     (.render template)))
  ([#^String text]
   (->template text {})))

(defn generate-migration! [args]
  (require 'clojure.java.io)
  (let [migration-name (-> (clojure.string/join " " args)
                         clojure.string/trim 
                         (clojure.string/replace #"[\s_-]+" "_")
                         (clojure.string/replace #"([A-Za-z0-9])([A-Z])" "$1_$2")
                         clojure.string/lower-case)
        calendar (Calendar/getInstance)
        year (.get calendar Calendar/YEAR)
        month (format "%02d" (inc (.get calendar Calendar/MONTH)))
        day (format "%02d" (.get calendar Calendar/DAY_OF_MONTH))
        hours (format "%02d" (.get calendar Calendar/HOUR_OF_DAY))
        minutes (format "%02d" (.get calendar Calendar/MINUTE))
        seconds (format "%02d" (.get calendar Calendar/SECOND))
        milliseconds (format "%03d" (.get calendar Calendar/MILLISECOND))
        prefix (str year month day hours minutes seconds milliseconds)
        migration-template (->template (slurp MIGRATION-TEMPLATE))]
    (doseq [direction ["up" "down"]]
      (let [filename (str prefix "_" migration-name "." direction ".sql")
            migrations-dir (File. MIGRATIONS-PATH)
            migration-file (File. (str MIGRATIONS-PATH "/" filename))]
        (if (or (.exists migrations-dir) (.mkdirs migrations-dir))
          (with-open [ostream (clojure.java.io/writer migration-file)]
            (.write ostream migration-template)
            (println "Generated migration:" (.getPath migration-file)))
          (binding [*out* *err*]
            (println "Failed to create directory:" (.getPath migrations-dir))))))))

(defn- generate-test! [args]
  (let [test-namespace (->> (flatten ["malea" "test" args])
                         (clojure.string/join "."))
        source-namespace (->> (flatten ["malea" args])
                           (clojure.string/join "."))
        test-path (-> (->> (flatten [TEST-PATH "malea" "test" args])
                        (clojure.string/join "/"))
                    (clojure.string/replace #"-" "_")
                    (str ".clj"))
        test-file (File. test-path)
        test-template (->template (slurp TEST-TEMPLATE)
                                  {"test_namespace" test-namespace
                                   "source_namespace" source-namespace})]
    (if (or (.. test-file getParentFile exists) (.. test-file getParentFile mkdirs))
      (with-open [ostream (clojure.java.io/writer test-file)]
        (.write ostream test-template)
        (println "Generated test file:" test-path))
      (binding [*out* *err*]
        (println "Failed to create directory:" (.getParent test-file))))))

(defn- parse-source-args [args]
  (cli args
       ["-t" "--[no-]test" "Generate corresponding test file." :default true]))

(defn generate-source! [args]
  (let [[options segments] (parse-source-args args)
        source-namespace (->> (flatten ["malea" segments])
                           (clojure.string/join "."))
        source-path (-> (->> (flatten [SOURCE-PATH "malea" segments])
                          (clojure.string/join "/"))
                      (clojure.string/replace #"-" "_")
                      (str ".clj"))
        source-file (File. source-path)
        source-template (->template (slurp SOURCE-TEMPLATE)
                                    {"namespace" source-namespace})]
    (if (or (.. source-file getParentFile exists) (.. source-file getParentFile mkdirs))
      (do
        (with-open [ostream (clojure.java.io/writer source-file)]
          (.write ostream source-template)
          (println "Generated source file:" source-path))
        (when (:test options)
          (generate-test! segments)))
      (binding [*out* *err*]
        (println "Failed to create directory:" (.getParent source-file))))))

(defn generate [project command & args]
  "Generates various types of files [lein migration generate migration name]"
  (case command
    "migration" (generate-migration! args)
    "source" (generate-source! args)
    "test" (generate-test! args)
    (lein.eval/eval-in-project project
      `(do
         (require 'malea.levenshtein
                  'malea.persistent-heap)
         (let [command# ~command
               transduce# (malea.levenshtein/transducer-from-list
                            ["migration" "source" "test"]
                            false malea.levenshtein/TRANSPOSITION)
               suggestions# (-> (transduce# command#)
                              (malea.persistent-heap/persistent-heap ;-> read the results into a heap
                                #(<= (or
                                       (malea.levenshtein/nonzero?
                                         (- (last %1) (last %2)))
                                       (compare (first %1) (first %2))) 0)))]
           (binding [*out* *err*]
             (println (str "ERROR: Unrecognized command: \"" command# "\""))
             (when-not (empty? suggestions#)
               (println "Did you mean one of these?")
               (loop [index# 1
                      [suggestion# distance#] (first suggestions#)
                      suggestions# (rest suggestions#)]
                 (println (str "  " index# ". \"" suggestion# "\" (Levenshtein distance: " distance# ")"))
                 (when-not (empty? suggestions#)
                   (recur (inc index#) (first suggestions#) (rest suggestions#)))))))
         nil))))

