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

(defproject malea "0.2.0"
  :description "Machine Learning for Information Retrieval and Natural Language Processing (among others)"
  :url "https://github.com/dylon/malea"
  :license {:name "The MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [incanter "1.4.1"]
                 [org.clojure/data.xml "0.0.6"]
                 [clojure-opennlp "0.2.0"]
                 [com.gfredericks/korma "0.3.0-beta11"]
                 [postgresql "9.1-901.jdbc4"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]
                                  [ragtime/ragtime.sql.files "0.3.2"]]
                   :plugins [[lein-midje "2.0.1"]
                             [ragtime/ragtime.lein "0.3.2"]
                             [org.antlr/ST4 "4.0.4"]]
                   :ragtime {:migrations ragtime.sql.files/migrations
                             :database "jdbc:postgresql:malea_wikipedia?user=malea"}}}
  :source-paths ["src/clojure/"])
