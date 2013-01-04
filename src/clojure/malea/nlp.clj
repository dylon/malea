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
  (:require [opennlp.nlp :as nlp])
  (:import net.java.textilej.parser.MarkupParser
           net.java.textilej.parser.builder.HtmlDocumentBuilder
           net.java.textilej.parser.markup.mediawiki.MediaWikiDialect
           javax.swing.text.html.HTMLEditorKit$ParserCallback
           javax.swing.text.html.parser.ParserDelegator
           java.io.StringReader
           java.io.StringWriter))

(def get-sentences
  "Extracts the natural language sentences from a body of text."
  (nlp/make-sentence-detector
    (clojure.java.io/resource "opennlp/models/en-sent.bin")))

(def tokenize
  "Tokenizes a natural language string into grams."
  (nlp/make-tokenizer
    (clojure.java.io/resource "opennlp/models/en-token.bin")))

(defn- rotate-onto-trigram [trigram gram]
  "Shifts the existing elements of the trigram one index to the left, discards
  the previously first element, and associates the last index with the new
  gram."
  (vector (get trigram 1) (get trigram 2) gram))

(defn trigrams [tokens]
  "Extracts all the trigrams from a vector of tokens."
  (loop [trigrams []
         trigram [nil nil nil]
         tokens tokens]
    (if (empty? tokens)
      (if (nil? (last trigram)) trigrams
        (let [trigram (rotate-onto-trigram trigram nil)
              trigrams (conj trigrams trigram)
              trigram (rotate-onto-trigram trigram nil)]
          (conj trigrams trigram)))
      (let [token (first tokens)
            trigram (rotate-onto-trigram trigram token)]
        (recur (conj trigrams trigram) trigram (rest tokens))))))

(defn- rotate-onto-bigram [bigram gram]
  "Shifts the existing elements of the bigram one index to the left, discards
  the previously first element, and associates the last index with the new
  gram."
  (vector (get bigram 1) gram))

(defn bigrams [tokens]
  "Extracts all the bigrams from a vector of tokens."
  (loop [bigrams []
         bigram [nil nil]
         tokens tokens]
    (if (empty? tokens)
      (if (nil? (last bigram)) bigrams
        (let [bigram (rotate-onto-bigram bigram nil)]
          (conj bigrams bigram)))
      (let [token (first tokens)
            bigram (rotate-onto-bigram bigram token)]
        (recur (conj bigrams bigram) bigram (rest tokens))))))

(defn unigrams [tokens]
  "Extracts all the unigrams from a vector of tokens."
  (map vector tokens))

(defn frequency [n-grams]
  "Determines the frequency of each distinct n-gram, and returns a mapping of
  each n-gram to the number of times it appeared in the collection.  This method
  can be applied to collections of things other than n-grams, but it was
  intended for n-grams."
  (loop [frequency {}
         n-grams n-grams]
    (if (empty? n-grams) frequency
      (let [n-gram (first n-grams)]
        (recur (assoc frequency n-gram (inc (get frequency n-gram 0)))
               (rest n-grams))))))

;; TODO: Consider normalizing the text by expanding contractions: 
;;
;; - http://en.wikipedia.org/wiki/Wikipedia:List_of_English_contractions
;; - http://en.wikipedia.org/wiki/Contraction_(grammar)#English
;;
;; TODO: Consider writing a custom parser:
;;
;; - http://www.mediawiki.org/wiki/Markup_spec
(defn preprocess [markup]
  "Preprocesses the document for further refinement."

  ;; The algorithm for transforming the MediaWiki markup into plain text came
  ;; from this StackOverflow reply:
  ;;
  ;; - http://stackoverflow.com/a/2865012/206543
  ;;
  ;; Additional list of WikiMedia parsers:
  ;;
  ;; - http://www.mediawiki.org/wiki/Alternative_parsers

  (let [^StringWriter writer (StringWriter.)
        ^HtmlDocumentBuilder builder
          (doto (HtmlDocumentBuilder. writer)
            (.setEmitAsDocument false))
        ^MarkupParser parser
          (doto (MarkupParser. (MediaWikiDialect.))
            (.setBuilder builder)
            (.parse markup))
        ^String html (.toString writer)
        ^StringBuilder cleaned (StringBuilder.)
        ^HTMLEditorKit$ParserCallback callback
          (proxy [HTMLEditorKit$ParserCallback] []
            (handleText [^chars data pos]
              (-> (.append cleaned (String. data))
                (.append " "))))]

    (.parse (ParserDelegator.) (StringReader. html) callback false)
    (-> (clojure.string/lower-case cleaned)
      (clojure.string/replace
        #"(?x)
        \{\{[^}]*}}
        |
        \[[a-z]+://\S+\s([^\]]*)]
        |
        <ref\b[^>]*>.*?</ref>
        |
        '{2,}" "$1"))))

;; vim: set ft=clojure:
