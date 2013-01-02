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

(ns malea.test.ma-fsa
  (:use malea.ma-fsa
        midje.sweet)
  (:require [malea.test.data :as data :only [dictionary]]))

(let [state (ma-fsa-state)]
  (fact
    (transitions state) => {}
    (final? state) => false)

  (finalize! state)

  (fact
    (final? state) => true))

(let [state (ma-fsa-state true)]
  (fact
    (transitions state) => {}
    (final? state) => true))

(let [start-state (ma-fsa-state)
      state-1 (ma-fsa-state)
      state-2 (ma-fsa-state)]

  (fact
    (transitions start-state) => {}
    (transitions state-1) => {}
    (transitions state-2) => {})

  (doto start-state
    (add-transition! \a state-1)
    (add-transition! \b state-2))

  (fact
    (transitions start-state) => {\a state-1, \b state-2}
    (transitions state-1) => {}
    (transitions state-2) => {}
    (transition start-state \a) => state-1 
    (transition start-state \b) => state-2))

(let [state-1 (ma-fsa-state)
      state-2 (ma-fsa-state)
      start-state (ma-fsa-state true {\y state-1, \z state-2})]
  (fact
    (transitions start-state) => {\y state-1, \z state-2}
    (transitions state-1) => {}
    (transitions state-2) => {}
    (final? start-state) => true
    (final? state-1) => false
    (final? state-2) => false))

(let [dawg (ma-fsa '())]
  (fact
    (accepts? dawg "") => false))

(let [dawg (ma-fsa '(""))]
  (fact
    (accepts? dawg "") => true))

(let [sample-size 1000
      [include-set exclude-set] (split-at (/ sample-size 2) data/dictionary)
      dawg (ma-fsa include-set)]
  (doseq [term include-set]
    (fact
      (accepts? dawg term) => true))
  (doseq [term exclude-set]
    (fact
      (accepts? dawg term) => false)))

