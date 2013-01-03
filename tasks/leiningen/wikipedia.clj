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

(defn- parse-args [args]
  (cli args
    ["-t" "--train" "Trains a model of the type specified."]))

(defn wikipedia [project & args]
  (let [[options args banner] (parse-args args)]
    (cond
      (:train options)
        (case (:train options)
          "corpus"
            (lein.eval/eval-in-project project
              `(do
                 (require 'malea.wikipedia
                          'malea.database)
                 (malea.database/establish-malea-db-connection!)
                 (with-open [xml-resource#
                              (clojure.java.io/input-stream
                                (clojure.java.io/resource
                                  "wikipedia/enwiki-latest-pages-articles.xml"))]
                   (malea.wikipedia/insert-pages-from-xml xml-resource#))))
          (binding [*out* *err*]
            (println "The only accepted command is \"lein wikipedia --train corpus\"")
            (System/exit 1)))
      :else
        (binding [*out* *err*]
          (println "The only accepted command is \"lein wikipedia --train corpus\"")
          (System/exit 1)))))

