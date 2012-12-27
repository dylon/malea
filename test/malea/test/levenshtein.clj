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

(ns malea.test.levenshtein
  (:use malea.levenshtein
        ;malea.test.dictionary
        ;incanter.stats
        midje.sweet)
  (:require malea.ma-fsa))

;(prn (transducer-from-list '()))
;(prn (transducer-from-list '() true))
;(prn (transducer-from-list '() false))
;(prn (transducer-from-list '() true STANDARD))
;(prn (transducer-from-list '() true TRANSPOSITION))
;(prn (transducer-from-list '() true MERGE-AND-SPLIT))
;(prn (transducer-from-list '() false STANDARD))
;(prn (transducer-from-list '() false TRANSPOSITION))
;(prn (transducer-from-list '() false MERGE-AND-SPLIT))
;(prn (transducer (malea.ma-fsa/ma-fsa '())))
;(prn (transducer (malea.ma-fsa/ma-fsa '()) STANDARD))
;(prn (transducer (malea.ma-fsa/ma-fsa '()) TRANSPOSITION))
;(prn (transducer (malea.ma-fsa/ma-fsa '()) MERGE-AND-SPLIT))

(defmacro some? [predicate collection]
  `(not (nil? (some ~predicate ~collection))))

;; QUES: Should I allow empty strings?  Currently, the algorithms do not support
;; empty strings.
;(let [dictionary '("")]
  ;(let [transduce (transducer-from-list dictionary STANDARD)]
    ;(fact
      ;(some? #{["" 0]} (transduce "")) => true)))

(let [dictionary '("bar"
                   "baz"
                   "corge"
                   "foo"
                   "fred"
                   "garply"
                   "grault"
                   "plugh"
                   "quux"
                   "qux"
                   "thud"
                   "waldo"
                   "xyzzy")]
  (let [transduce (transducer-from-list dictionary true STANDARD)]
    (fact
      (empty? (transduce "f" 0)) => true)
    (fact
      (empty? (transduce "f" 1)) => true)
    (fact
      (= #{["foo" 2]}
         (set (transduce "f" 2))) => true)
    (fact
      (= #{["foo" 2] ["fred" 3] ["bar" 3] ["baz" 3] ["qux" 3]}
         (set (transduce "f" 3))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4]}
         (set (transduce "f" 4))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]}
         (set (transduce "f" 5))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5] ["garply" 6] ["grault" 6]}
         (set (transduce "f" 6))) => true))
  (let [transduce (transducer-from-list dictionary true TRANSPOSITION)]
    (fact
      (empty? (transduce "f" 0)) => true)
    (fact
      (empty? (transduce "f" 1)) => true)
    (fact
      (= #{["foo" 2]}
         (set (transduce "f" 2))) => true)
    (fact
      (= #{["foo" 2] ["fred" 3] ["bar" 3] ["baz" 3] ["qux" 3]}
         (set (transduce "f" 3))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4]}
         (set (transduce "f" 4))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]}
         (set (transduce "f" 5))) => true)
    (fact
      (= #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4] ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5] ["garply" 6] ["grault" 6]}
         (set (transduce "f" 6))) => true))
  (let [transduce (transducer-from-list dictionary true MERGE-AND-SPLIT)]
    (fact
      (empty? (transduce "f" 0)) => true)
    (fact
      (empty? (transduce "f" 1)) => true)
    (fact
      (= #{["bar" 2] ["baz" 2] ["foo" 2] ["qux"]}
         (set (transduce "f" 2))) => true)
    (fact
      (= #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2] ["fred" 3] ["quux" 3] ["thud" 3]}
         (set (transduce "f" 3))) => true)
    (fact
      (= #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2] ["fred" 3] ["quux" 3] ["thud" 3] ["corge" 4] ["plugh" 4] ["waldo" 4] ["xyzzy" 4]}
         (set (transduce "f" 4))) => true)
    (fact
      (= #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2] ["fred" 3] ["quux" 3] ["thud" 3] ["corge" 4] ["plugh" 4] ["waldo" 4] ["xyzzy" 4] ["garply" 5] ["grault" 5]}
         (set (transduce "f" 5))) => true)))

