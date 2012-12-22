(ns malea.ma-fsa)

(declare ma-fsa ma-fsa-node)

(defn- longest-common-prefix-length [word-1 word-2]
  (let [upper-bound (min (count word-1) (count word-2))]
    (loop [index 0]
      (if-not
        (and
          (< index upper-bound)
          (= (nth word-1 index)
             (nth word-2 index)))
        index
        (recur (inc index))))))

(defn- let! [atomic-value new-value]
  (compare-and-set! atomic-value @atomic-value new-value))

(defprotocol INode
  (edges [this])
  (edge [this label])
  (final? [this])
  (add-edge! [this label node])
  (finalize! [this])
  (signature [this]))

(defrecord Node [final edges]
  INode
  (edges [this]
    (deref (:edges this)))
  (edge [this label]
    ((deref (:edges this)) label))
  (final? [this]
    (deref (:final this)))
  (add-edge! [this label node]
    (swap! (:edges this) assoc label node)
    this)
  (finalize! [this]
    (compare-and-set! (:final this) false true)
    this))

(defprotocol IMaFsa
  (insert [this word])
  (finish! [this])
  (minimize! [this lower-bound])
  (accepts? [this word]))

(defrecord MaFsa [previous-word root unchecked-nodes minimized-nodes]

  IMaFsa

  (insert [this word]
    (let [previous-word (:previous-word this)
          lower-bound (longest-common-prefix-length word @previous-word)]
      (minimize! this lower-bound)
      (let [unchecked-nodes (:unchecked-nodes this)
            node (atom (if (empty? @unchecked-nodes)
                         (:root this)
                         (last (first @unchecked-nodes))))
            word-length (count word)]
        (loop [i lower-bound]
          (when (< i word-length)
            (let [character (nth word i)
                  next-node (ma-fsa-node)]
              (add-edge! @node character next-node)
              (swap! unchecked-nodes #(cons [@node character next-node] %))
              (let! node next-node)
              (recur (inc i)))))
        (finalize! @node)
        (let! previous-word word))
      this))

  (finish! [this]
    (minimize! this 0))

  (minimize! [this lower-bound]
    (let [minimized-nodes (:minimized-nodes this)
          unchecked-nodes (:unchecked-nodes this)]
      (loop [j (count @unchecked-nodes)]
        (when (> j lower-bound)
          (let [[parent character child] (first @unchecked-nodes)
                minimized-node (@minimized-nodes child)]
            (swap! unchecked-nodes rest)
            (if minimized-node
              (add-edge! parent character minimized-node)
              (swap! minimized-nodes conj child)))
          (recur (dec j))))
      this))

  (accepts? [this word]
    (let [word-length (count word)]
      (loop [node (:root this)
             index 0]
        (if (nil? node)
          false
          (if (= index word-length)
            (final? node)
            (let [label (nth word index)]
              (recur (edge node label) (inc index)))))))))

(defn ma-fsa-node
   ([final edges]
    (Node. (atom final) (atom edges)))
   ([final]
    (ma-fsa-node final {}))
   ([]
    (ma-fsa-node false)))

(defn ma-fsa
  ([dictionary sorted]
   (let [dictionary (if-not sorted (sort dictionary) dictionary)
         previous-word (atom "")
         root (ma-fsa-node)
         unchecked-nodes (atom '())
         minimized-nodes (atom #{})
         dawg (MaFsa. previous-word root unchecked-nodes minimized-nodes)]
     (finish!
       (reduce #(insert %1 %2) dawg dictionary))))
  ([dictionary]
   (ma-fsa dictionary false)))

