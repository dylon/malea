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

(ns malea.wikipedia
  (:use korma.core)
  (:require [malea.database :as db]))

(db/initialize!)

(declare page gram_1 gram_2 gram_3 trigram bigram unigram)

(defentity page
  (table :pages)
  (entity-fields :text)
  (has-many trigram)
  (has-many bigram)
  (has-many unigram))

;; Workaround for Korma: It doesn't support multiple belongs-to relationships.
;; See: https://groups.google.com/forum/?fromgroups=#!topic/sqlkorma/asnjrVh0IYQ
(defentity gram_1
  (table :grams)
  (entity-fields :value)
  (has-many trigram))
(defentity gram_2
  (table :grams)
  (entity-fields :value)
  (has-many trigram))
(defentity gram_3
  (table :grams)
  (entity-fields :value)
  (has-many trigram))

(defentity trigram
  (table :trigrams)
  (entity-fields :frequency)
  (belongs-to page)
  (belongs-to gram_1)
  (belongs-to gram_2)
  (belongs-to gram_3))

(defentity bigram 
  (table :bigrams)
  (entity-fields :frequency)
  (belongs-to page)
  (belongs-to gram_1)
  (belongs-to gram_2))

(defentity unigram  
  (table :unigrams)
  (entity-fields :frequency)
  (belongs-to page)
  (belongs-to gram_1))

