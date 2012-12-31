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
                             [incanter "1.4.1"]
                             [ragtime/ragtime.lein "0.3.2"]]
                   :ragtime {:migrations ragtime.sql.files/migrations
                             :database "jdbc:postgresql://localhost:5432/malea_wikipedia?user=malea"}}}
  :source-paths ["src/clojure/"])
