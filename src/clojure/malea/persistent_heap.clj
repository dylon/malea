;; Copyright (c) 2013 Dylon Edwards
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

;; The original source was taken and modified from the following Gist:
;; - https://gist.github.com/781061   

(ns malea.persistent-heap)

(defn max-element [element-1 element-2 at-least?]
  (if (at-least? element-1 element-2) element-1 element-2))
 
(defn swap [heap left-index right-index]
  "Swaps two elements in the heap."
  (assoc heap
         left-index (get heap right-index)
         right-index (get heap left-index)))
 
(defn children [index]
  "Left and right indices of the children of the node at index."
  (let [left-index (inc (* index 2))
        right-index (inc left-index)]
    [left-index right-index]))
 
(defn parent-index [index]
  "Index of the parent node to that at index."
  (if (not= 0 index)
    (/ (- index (if (odd? index) 1 2)) 2)
    nil))
 
(defn tree
  "Converts the heap into a tree that can be walked."
  ([heap] (tree heap 0))
  ([heap index]
   (let [[left-index right-index] (children index)
         node (get heap index nil)]
     (when node
       [node (tree heap left-index) (tree heap right-index)]))))
 
(defn heap-up 
  "Propogates elements upward to maintain the heap property."
  ([heap] (heap-up heap >= (dec (count heap))))
  ([heap at-least?] (heap-up heap at-least? (dec (count heap)))) 
  ([heap at-least? index]
   (if-let [parent-index (parent-index index)]
     (if (at-least? (get heap index) (get heap parent-index))
       (recur (swap heap index parent-index) at-least? parent-index)
       heap)
     heap)))
 
(defn heap-down
  "Propogates elements downward to maintain the heap property."
  ([heap] (heap-down (pop (swap heap 0 (dec (count heap)))) >= 0))
  ([heap at-least?] (heap-down (pop (swap heap 0 (dec (count heap)))) at-least? 0))
  ([heap at-least? index]
   (let [[left-index right-index] (children index)
         left-child (get heap left-index nil)
         right-child (get heap right-index nil)
         node (get heap index nil)]
     (if (and node left-child right-child)
       (cond
         (at-least? left-child (max-element right-child node at-least?))
           (recur (swap heap index left-index) at-least? left-index)
         (at-least? right-child (max-element left-child node at-least?))
           (recur (swap heap index right-index) at-least? right-index)
         :else heap)
       heap))))

(deftype PersistentHeap [heap at-least?]
  clojure.lang.ISeq
  (first [this] (first heap))
  (next [this]
    (-> heap
      (heap-down at-least?)
      (PersistentHeap. at-least?)))
  (more [this] (.next this))
  (cons [this obj]
    (-> heap
      (conj obj)
      (heap-up at-least?)
      (PersistentHeap. at-least?)))
  (seq [this]
    (seq
      (loop [collection []
             heap heap]
        (if (empty? heap) collection
          (recur (conj collection (first heap))
                 (heap-down heap at-least?))))))
  (count [this] (count heap))
  (empty [this] (PersistentHeap. [] at-least?))
  (equiv [this other] (= other heap)))

;; TODO: Implement the more efficient max-heapify algorithm from which a heap
;; may be built from a collection.
;;
;; Heap Algorithms:
;; http://courses.csail.mit.edu/6.006/spring11/rec/rec09.pdf 
 
(defn persistent-heap
  ([collection]
   (persistent-heap collection >=)) 
  ([collection at-least?]
   (into (PersistentHeap. [] at-least?) collection)))
 
(defn heapsort
  ([collection]
   (heapsort collection >=)) 
  ([collection at-least?]
   (->> (persistent-heap collection at-least?)
        (iterate rest)
        (take (count collection))
        (map first))))

;; vim: set ft=clojure:
