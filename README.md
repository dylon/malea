# malea

This will be the home of many of my machine learning endeavors into infomation
retrieval and natural language processing, among others.  I am currently porting
my Levenshtein Automata library from CoffeeScript to Clojure to serve as the
basis for selecting candidate words for context-sensitive spelling correction,
using a language model trained on some corpus I have yet to select.  It is very
much in the priliminary stages of its life, so please do not expect it to do
anything fancy yet.

## Usage

Currently, there isn't much you can do other than run tests:

```sh
lein midje
```

You can use the MA-FSA that I just added, if you would like:
```clojure
(ns foo
  (:use malea.ms-fsa))

;; Let `dictionary` be some list of terms.
(let [dawg (ms-fsa dictionary)]
  (accepts? dawg "bar")) ;-> Whether "bar" was among the terms of the dictionary
```

## License

Copyright © 2012 Dylon Edwards

Distributed under the MIT License.
