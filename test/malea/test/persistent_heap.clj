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

(ns malea.test.persistent-heap
  (:use malea.persistent-heap
        midje.sweet))

(fact "Equivalent Heaps"
  (let [heap-1 (persistent-heap [])
        heap-2 (persistent-heap [])]
    (= heap-1 heap-2)) => true)

(fact "Binary Heap of Integers"
  (let [heap (persistent-heap [3 1 5 2 4 9 6 8 7 0])]
    (seq heap)) => (just [9 8 7 6 5 4 3 2 1 0]))

(let [element (fn [value] {:value value})
      at-least? (fn [element-1 element-2]
                  (>= (compare (:value element-1) (:value element-2)) 0))]
  (loop [heap (persistent-heap (map element [\a \b \z \c \y \e \d]) at-least?)
         previous-node (element \u9999)]
    (when-not (empty? heap)
      (let [node (first heap)]
        (fact "Node should be no Greater than the Previous"
          (at-least? previous-node node) => true)
        (recur (next heap) node)))))

;; vim: set ft=clojure:
