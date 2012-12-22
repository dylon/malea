(defproject malea "0.1.0-alpha"
  :description "Economic modeling using statistical and machine learning techniques"
  :url "https://github.com/dylon/malea"
  :license {:name "The MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [incanter "1.4.1"]]
  :profiles {:dev {:dependencies [[midje "1.4.0"]]
                   :plugins [[lein-midje "2.0.1"]]}})
