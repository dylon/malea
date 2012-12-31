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
  (:use [incanter.stats :only (sample-uniform)]))

(def ^:private ^:const migrations-path "migrations")

(defn- generate! [args]
  (let [migration-name (-> (clojure.string/join " " args)
                         clojure.string/trim 
                         (clojure.string/replace #"[\s_-]+" "_")
                         (clojure.string/replace #"([A-Za-z0-9])([A-Z])" "$1_$2")
                         clojure.string/lower-case)
        calendar (java.util.Calendar/getInstance)
        year (.get calendar java.util.Calendar/YEAR)
        month (format "%02d" (inc (.get calendar java.util.Calendar/MONTH)))
        day (format "%02d" (.get calendar java.util.Calendar/DAY_OF_MONTH))
        nonce (first (sample-uniform 1 :min 100000 :max 999999 :integers true))
        prefix (str year month day nonce)]
    (doseq [direction ["up" "down"]]
      (let [filename (str prefix "_" migration-name "." direction ".sql")
            migrations-dir (java.io.File. migrations-path)
            migration-file (java.io.File. (str migrations-path "/" filename))]
        (if (or (.exists migrations-dir) (.mkdir migrations-dir))
          (if (.createNewFile migration-file)
            (println "Generated migration:" (.getPath migration-file))
            (println "Failed to generate migration:" (.getPath migration-file)))
          (println "Failed to create directory:" (.getPath migrations-dir)))))))

(defn generate [project task & args]
  "Generates various types of files [lein migration generate migration name]"
  (case task
    "generate" (generate! args)
    (throw (Exception. "Expected \"task\" to be \"migration\""))))

