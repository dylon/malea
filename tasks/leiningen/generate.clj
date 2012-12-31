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
  (:require [leiningen.core.eval :as lein.eval])
  (:import java.util.Calendar
           org.stringtemplate.v4.ST))

(def ^:private ^:const MIGRATIONS-PATH "migrations")
(def ^:private ^:const MIGRATION-TEMPLATE "src/stringtemplate/postgresql/migration.sql.stg")

(defn ->template
  [#^String text #^java.util.Map attributes]
  (let [template (ST. text)]
    (doseq [[#^String name #^Object value] attributes]
      (.add template name value))
    (.render template)))

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
        template (->template (slurp MIGRATION-TEMPLATE) {"year" year})]
    (doseq [direction ["up" "down"]]
      (let [filename (str prefix "_" migration-name "." direction ".sql")
            migrations-dir (java.io.File. MIGRATIONS-PATH)
            migration-file (java.io.File. (str MIGRATIONS-PATH "/" filename))]
        (if (or (.exists migrations-dir) (.mkdir migrations-dir))
          (with-open [ostream (clojure.java.io/writer migration-file)]
            (.write ostream template)
            (println "Generated migration:" (.getPath migration-file)))
          (println "Failed to create directory:" (.getPath migrations-dir)))))))

(defn generate [project command & args]
  "Generates various types of files [lein migration generate migration name]"
  (case command
    "migration" (generate-migration! args)
    (lein.eval/eval-in-project project
      `(do
         (require 'malea.levenshtein)
         (let [command# ~command
               transduce# (malea.levenshtein/transducer-from-list
                            ["migration"] false malea.levenshtein/TRANSPOSITION)
               suggestions# (->> (transduce# command#)
                             (sort #(or
                                      (malea.levenshtein/nonzero?
                                        (- (last %1) (last %2)))
                                      (compare (first %1) (first %2)))))]
           (binding [*out* *err*]
             (println (str "ERROR: Unrecognized command: \"" command# "\""))
             (when-not (empty? suggestions#)
               (println "Did you mean one of these?")
               (loop [index# 1
                      [suggestion# distance#] (first suggestions#)
                      suggestions# (rest suggestions#)]
                 (println (str "  " index# ". \"" suggestion# "\" (Levenshtein distance: " distance# ")"))
                 (when-not (empty? suggestions#)
                   (recur (inc index#) (first suggestions#) (rest suggestions#)))))))))))

