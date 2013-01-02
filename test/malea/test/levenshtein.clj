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
        midje.sweet)
  (:require malea.ma-fsa))

;(defmacro some? [predicate collection]
  ;`(not (nil? (some ~predicate ~collection))))

;; QUES: Should I allow empty strings?  Currently, the algorithms do not support
;; empty strings.
;(let [dictionary '("")]
  ;(let [transduce (transducer-from-list dictionary STANDARD)]
    ;(fact
      ;(some? #{["" 0]} (transduce "")) => true)))

(let [dictionary '("bar" "baz" "corge" "foo" "fred" "garply" "grault" "plugh"
                     "quux" "qux" "thud" "waldo" "xyzzy")]

  (let [transduce (transducer-from-list dictionary true STANDARD)]
    (fact
      (transduce "f" 0) => empty?)
    (fact
      (transduce "f" 1) => empty?)
    (fact
      (transduce "f" 2) => (just #{["foo" 2]}))
    (fact
      (transduce "f" 3) =>
      (just #{["foo" 2] ["fred" 3] ["bar" 3] ["baz" 3] ["qux" 3]}))
    (fact
      (transduce "f" 4) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3]
              ["qux" 3] ["quux" 4] ["thud" 4]}))
    (fact
      (transduce "f" 5) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4]
              ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]}))
    (fact
      (transduce "f" 6) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4]
              ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]
              ["garply" 6] ["grault" 6]})))

  (let [transduce (transducer-from-list dictionary true TRANSPOSITION)]
    (fact
      (transduce "f" 0) => empty?)
    (fact
      (transduce "f" 1) => empty?)
    (fact
      (transduce "f" 2) => (just #{["foo" 2]}))
    (fact
      (transduce "f" 3) =>
      (just #{["foo" 2] ["fred" 3] ["bar" 3] ["baz" 3] ["qux" 3]}))
    (fact
      (transduce "f" 4) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3]
              ["qux" 3] ["quux" 4] ["thud" 4]}))
    (fact
      (transduce "f" 5) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4]
              ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]}))
    (fact
      (transduce "f" 6) =>
      (just #{["foo" 2] ["bar" 3] ["baz" 3] ["fred" 3] ["qux" 3] ["quux" 4]
              ["thud" 4] ["corge" 5] ["plugh" 5] ["waldo" 5] ["xyzzy" 5]
              ["garply" 6] ["grault" 6]})))

  (let [transduce (transducer-from-list dictionary true MERGE-AND-SPLIT)]
    (fact
      (transduce "f" 0) => empty?)
    (fact
      (transduce "f" 1) => empty?)
    (fact
      (transduce "f" 2) => (just #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2]}))
    (fact
      (transduce "f" 3) =>
      (just #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2]
              ["fred" 3] ["quux" 3] ["thud" 3]}))
    (fact
      (transduce "f" 4) =>
      (just #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2] ["fred" 3] ["quux" 3]
              ["thud" 3] ["corge" 4] ["plugh" 4] ["waldo" 4] ["xyzzy" 4]}))
    (fact
      (transduce "f" 5) =>
      (just #{["bar" 2] ["baz" 2] ["foo" 2] ["qux" 2] ["fred" 3] ["quux" 3]
              ["thud" 3] ["corge" 4] ["plugh" 4] ["waldo" 4] ["xyzzy" 4]
              ["garply" 5] ["grault" 5]}))))

