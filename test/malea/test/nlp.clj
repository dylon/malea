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

(ns malea.test.nlp
  (:use malea.nlp
        midje.sweet))

(fact "Extracting Sentences"
  (let [document ""]
    (get-sentences document)) => (just [])
  (let [document "This is a sentence."]
    (get-sentences document)) => (just ["This is a sentence."])
  (let [document "This is a sentence. This is another one from Dr. Insanity!"]
    (get-sentences document)) =>
      (just ["This is a sentence." "This is another one from Dr. Insanity!"]))

(fact "Extracting Tokens"
  (let [sentence ""]
    (tokenize sentence)) => (just [])
  (let [sentence "This is another one from Dr. Insanity!"]
    (tokenize sentence)) =>
      (just ["This" "is" "another" "one" "from" "Dr." "Insanity" "!"]))

(fact "Extracting Trigrams"
  (trigrams []) => (just [])
  (trigrams [\a]) => (just [[nil nil \a] [nil \a nil] [\a nil nil]])
  (trigrams [\a \b]) =>
    (just [[nil nil \a] [nil \a \b] [\a \b nil] [\b nil nil]])
  (trigrams [\a \b \c]) =>
    (just [[nil nil \a] [nil \a \b] [\a \b \c] [\b \c nil] [\c nil nil]]))

(fact "Extracting Bigrams"
  (bigrams []) => (just [])
  (bigrams [\a]) => (just [[nil \a] [\a nil]])
  (bigrams [\a \b]) => (just [[nil \a] [\a \b] [\b nil]]))

(fact "Extracting Unigrams"
  (unigrams []) => (just [])
  (unigrams [\a]) => (just [[\a]])
  (unigrams [\a \b]) => (just [[\a] [\b]]))

(fact "Determining N-Gram Frequencies"
  (frequency []) => (just {})
  (frequency [[\a]]) => (just {[\a] 1})
  (frequency [[\a] [\b]]) => (just {[\a] 1, [\b] 1})
  (frequency [[\a] [\b] [\a]]) => (just {[\a] 2, [\b] 1}))

(fact "Preprocessing a Document"
  (preprocess "") => (just "")
  (preprocess "This is a test.") => (just "this is a test.")
  (preprocess "This is a sentence. This is another one from Dr. Insanity!") =>
      (just "this is a sentence. this is another one from dr. insanity!"))

;; vim: set ft=clojure:
