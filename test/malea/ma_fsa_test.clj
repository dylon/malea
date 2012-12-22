(ns malea.ma-fsa-test
  (:use malea.ma-fsa
        midje.sweet
        incanter.stats))

(let [node (ma-fsa-node)]
  (fact
    (edges node) => {})
  (fact
    (final? node) => false))

(let [node (ma-fsa-node true)]
  (fact
    (edges node) => {})
  (fact
    (final? node) => true))

(let [root (ma-fsa-node)
      child-1 (ma-fsa-node)
      child-2 (ma-fsa-node)]

  (fact
    (edges root) => {})

  (let [root (-> root
               (add-edge! "a" child-1)
               (add-edge! "b" child-2))]
    (fact
      (edges root) => {"a" child-1
                       "b" child-2})))

(with-open [istream (clojure.java.io/reader "/usr/share/dict/american-english")]
  (let [sample-size 1000   
        dictionary (sample (line-seq istream) :size sample-size :replacement false)
        include-set (sort (take (/ sample-size 2) dictionary))
        exclude-set (take (/ sample-size 2) (reverse dictionary))
        dawg (ma-fsa include-set)]
    (doseq [term include-set]
      (fact
        (accepts? dawg term) => true))
    (doseq [term exclude-set]
      (fact
        (accepts? dawg term) => false))))
(newline)
