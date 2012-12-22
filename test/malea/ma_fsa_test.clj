(ns malea.ma-fsa-test
  (:use malea.ma-fsa
        midje.sweet
        incanter.stats))

(let [node (ma-fsa-node)]
  (fact
    (edges node) => {})
  (fact
    (final? node) => false)
  (finalize! node)
  (fact
    (final? node) => true))

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
  (fact
    (edges child-1) => {})
  (fact
    (edges child-2) => {})

  (add-edge! root \a child-1)
  (add-edge! root \b child-2)

  (fact
    (edges root) => {\a child-1
                     \b child-2})
  (fact
    (edges child-1) => {})
  (fact
    (edges child-2) => {})
  (fact
    (edge root \a) => child-1)
  (fact
    (edge root \b) => child-2))

(let [child-1 (ma-fsa-node)
      child-2 (ma-fsa-node)
      root (ma-fsa-node true {\y child-1
                              \z child-2})]
  (fact
    (edges root) => {\y child-1
                     \z child-2})
  (fact
    (edges child-1) => {})
  (fact
    (edges child-2) => {})
  (fact
    (final? root) => true)
  (fact
    (final? child-1) => false)
  (fact
    (final? child-2) => false))

(with-open [istream (clojure.java.io/reader "/usr/share/dict/american-english")]
  (let [sample-size 1000   
        dictionary (sample (line-seq istream) :size sample-size :replacement false)
        include-set (take (/ sample-size 2) dictionary)
        exclude-set (take (/ sample-size 2) (reverse dictionary))
        dawg (ma-fsa include-set)]
    (doseq [term include-set]
      (fact
        (accepts? dawg term) => true))
    (doseq [term exclude-set]
      (fact
        (accepts? dawg term) => false))))
