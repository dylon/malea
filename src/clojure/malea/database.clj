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

(ns malea.database
  (:require korma.db)
  (:import com.mchange.v2.c3p0.ComboPooledDataSource))

(defn pool [{:keys [classname subprotocol subname user password]}]
  (let [datasource
          (doto (ComboPooledDataSource.)
            (.setDriverClass classname)
            (.setJdbcUrl (str "jdbc:"subprotocol":"subname))
            (.setUser user)
            (.setPassword password)
            ;; expire excess connections after 30 minutes of inactivity:
            (.setMaxIdleTimeExcessConnections (* 30 60))
            ;; expire connections after 3 hours of inactivity:
            (.setMaxIdleTime (* 3 30 60)))]
    {:datasource datasource}))

(let [db-connection (atom nil)]
  (defn current-db-connection
    ([] @db-connection)
    ([new-db-connection]
     (reset! db-connection new-db-connection))))

(defmacro ->SpecifyDatabase [^String db-name]
  (let [db-identifier
          (clojure.string/replace db-name \_ \-)
        db-spec
          (symbol (str db-identifier "-db-spec"))
        pooled-db
          (symbol (str "pooled-" db-identifier "-db"))
        db-connection
          (symbol (str db-identifier "-db-connection"))
        establish-db-connection
          (symbol (str "establish-" db-identifier "-db-connection!"))]
    `(do
       (def ~db-spec
         {:classname "org.postgresql.Driver"
          :subprotocol "postgresql"
          :subname ~(str "//127.0.0.1:5432/" db-name)
          :user "malea"})
       (def ~pooled-db
         (delay
            (korma.db/default-connection
              (current-db-connection
                (pool ~db-spec)))))
       (defn ~db-connection []
         (deref ~pooled-db))
       (defn ~establish-db-connection []
         "Syntactic sugar to make one's intentions clear when initializing the
         database."
         (~db-connection))
       nil)))

(->SpecifyDatabase "malea")
(->SpecifyDatabase "malea_test")

