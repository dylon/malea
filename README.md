# malea

This will be the home of many of my machine learning endeavors into infomation
retrieval and natural language processing, among others.  I am currently porting
my [Levenshtein Automata](https://github.com/dylon/levenshtein_automata) library
from CoffeeScript to Clojure, to serve as the basis for selecting candidate
words for context-sensitive spelling correction, using a language model trained
on some corpus I have yet to select.  It is very much in the priliminary stages
of its life, so please do not expect it to do anything fancy yet.

## Usage

Currently, there isn't much you can do other than run tests:

```sh
lein midje
```

If you would like, you may use the [MA-FSA](blob/master/src/malea/ma_fsa.clj):
```clojure
(ns foo
  (:use malea.ma-fsa))

;; Let `dictionary` be some list of terms.
(let [dawg (ma-fsa dictionary)]
  (accepts? dawg "bar")) ;-> Whether "bar" was among the terms of the dictionary
```

## License

Copyright © 2012 Dylon Edwards

Distributed under the [MIT License](http://www.opensource.org/licenses/mit-license.php).
