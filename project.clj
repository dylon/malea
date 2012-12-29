(defproject malea "0.2.0"
  :description "Machine Learning for Information Retrieval and Natural Language Processing (among others)"
  :url "https://github.com/dylon/malea"
  :license {:name "The MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [incanter "1.4.1"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]]
                   :plugins [[lein-midje "2.0.1"]]}}
  :source-paths ["src/clojure/"])
