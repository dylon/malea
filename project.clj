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

(defproject malea "0.3.0"
  :description "Machine Learning for Information Retrieval and Natural Language Processing (among others)"
  :url "https://github.com/dylon/malea"
  :license {:name "The MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 ;[incanter "1.4.1"]
                 ;[net.java/textile-j "2.2"]
                 [org.clojure/data.xml "0.0.7"]
                 [org.antlr/antlr4 "4.0-rc-1"]
                 [edu.stanford.nlp/stanford-corenlp "1.3.4"]
                 ;[clojure-opennlp "0.2.0"]
                 [korma "0.3.0-RC2"]
                 ;[postgresql "9.1-901.jdbc4"]
                 [puppetlabs/postgresql "9.2-1002.jdbc4"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [c3p0/c3p0 "0.9.1.2"]
                 [ragtime/ragtime.sql.files "0.3.2"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                    javax.jms/jms
                                                    com.sun.jdmk/jmxtools
                                                    com.sun.jmx/jmxri]]
                 [org.clojure/tools.logging "0.2.3"]]
  :plugins [[ragtime/ragtime.lein "0.3.2"]
            [org.antlr/stringtemplate "4.0.7"]]
  :profiles {:dev {:dependencies [[midje "1.5-alpha8"]]
                   :plugins [[lein-midje "3.0-alpha4"]
                             [org.clojure/tools.cli "0.2.2"]]
                   :ragtime {:migrations ragtime.sql.files/migrations
                             :database "jdbc:postgresql:malea?user=malea"}}
             :test [{:ragtime {:migrations ragtime.sql.files/migrations
                               :database "jdbc:postgresql:malea_test?user=malea"}}
                    :default]}
  :source-paths ["src/clojure/"]
  :java-source-paths ["src/java/"])
