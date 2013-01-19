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

(ns malea.nlp
  (:import [java.io StringReader]
           [org.antlr.v4.runtime ANTLRInputStream CommonTokenStream]
           [malea PreprocessorLexer PreprocessorParser]
           [edu.stanford.nlp.process CoreLabelTokenFactory PTBTokenizer]))

;; TODO: Consider normalizing the text by expanding contractions:
;;
;; - http://en.wikipedia.org/wiki/Wikipedia:List_of_English_contractions
;; - http://en.wikipedia.org/wiki/Contraction_(grammar)#English

;; WikiMedia Markup Specification:
;;
;; - http://www.mediawiki.org/wiki/Markup_spec

(defn count-n-grams [trie-node]
  "Counts the descendants of `trie-node`"
  (+ (count (keys trie-node))
    (reduce +
      (map count-n-grams
        (map #(:children %) (vals trie-node))))))

(defn insert-n-gram [trie n-gram]
  "Inserts an n-gram (collection of grams, having a size of n) into a trie, one
  gram at a time (sequentially, from the first gram in the collection to the
  last).  As well as inserting the n-gram into the trie, this will maintain the
  frequency of the the number of times the n-gram has appeared."
  (if (empty? n-gram) trie
    (let [gram (first n-gram)
          entry (get trie gram {:frequency 0, :children {}})
          entry (assoc entry :frequency (inc (:frequency entry)))]
      (assoc trie gram
             (assoc entry :children
                    (insert-n-gram (:children entry) (rest n-gram)))))))

(defn extract-n-grams
  "Extracts sub-collections of size n of consecutive tokens, with the first
  (n-1) elements of the first collection and the last (n-1) elements of the last
  collection being padded with `nil`."
  ([n tokens]
   (let [n-grams '()
         buffer (vec (repeat n nil))
         tokens (concat tokens (repeat (dec n) nil))]
     (extract-n-grams n-grams buffer n tokens)))
  ([n-grams buffer n tokens]
   (lazy-seq
     (when-not (empty? tokens)
       (let [buffer (conj (subvec buffer 1 n) (first tokens))
             n-grams (cons buffer n-grams)]
         (cons buffer (extract-n-grams n-grams buffer n (rest tokens))))))))

(defn preprocess [markup]
  "Preprocesses the document for tokenization."
  (let [lexer (PreprocessorLexer. (ANTLRInputStream. markup))
        parser (PreprocessorParser. (CommonTokenStream. lexer))]
    (.. parser preprocess preprocessed)))

(defn tokenize [text]
  "Tokenizes a string of English, natural language text."
  (let [reader (StringReader. text)
        tokenizer
          (PTBTokenizer. reader (CoreLabelTokenFactory.)
            (clojure.string/join "," [
              "ptb3Escaping=false"
              "normalizeParentheses=false"
              "latexQuotes=false"
              "unicodeQuotes=true" ;; for Postgres
              "asciiQuotes=false"
              "escapeForwardSlashAsterisk=false"]))]
    (map #(.value %) (iterator-seq tokenizer))))

;; vim: set ft=clojure et sta ts=2 sw=2:
