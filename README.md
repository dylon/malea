# Malea

This will be the home of many of my machine learning endeavors into infomation
retrieval and natural language processing, among others.  I am currently porting
my [Levenshtein Automata](levenshtein_automata) library from CoffeeScript to
Clojure, to serve as the basis for selecting candidate words for
context-sensitive spelling correction, using a language model trained on some
corpus I have yet to select.  It is very much in the priliminary stages of its
life, so please do not expect it to do anything fancy yet.

## Preconditions

I assume you are using some variant of Linux and are a Postgres super user.

## Preparation

### Dependencies

Make sure your dependencies are up-to-date:

```
$ lein deps
```

### PostgreSQL

This project is configured under the assumption that Postgres is being run
locally and is listening to the default port (5432).

Create the database and `malea` user:

```
$ createdb malea_wikipedia -E UTF8
$ createuser malea
$ psql -d malea_wikipedia
psql (9.2.2)
Type "help" for help.

malea_wikipedia=# REVOKE connect ON DATABASE malea_wikipedia FROM PUBLIC;
REVOKE
malea_wikipedia=# GRANT connect ON DATABASE malea_wikipedia TO malea;
GRANT
malea_wikipedia=> \q
```

Run the migrations:

```
$ lein ragtime migrate
```

### Wikipedia

Download the latest English Wikipedia dump:

```
$ wget -c http://download.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles.xml.bz2 -O resources/wikipedia/enwiki-latest-pages-articles.xml.bz2
$ cd resources/wikipedia/
$ bunzip2 enwiki-latest-pages-articles.xml.bz2
$ cd -
```

### OpenNLP

Download the English-based, OpenNLP models from the following locations, and
place them into `resources/opennlp/models/`:

1. [Tokenizer](http://opennlp.sourceforge.net/models-1.5/en-token.bin "Trained on opennlp training data.")
	- `$ wget -c http://opennlp.sourceforge.net/models-1.5/en-token.bin -O resources/opennlp/models/en-token.bin`
2. [Sentence Detector](http://opennlp.sourceforge.net/models-1.5/en-sent.bin "Trained on opennlp training data.")
	- `$ wget -c http://opennlp.sourceforge.net/models-1.5/en-sent.bin -O resources/opennlp/models/en-sent.bin`

## Usage

To use the [MA-FSA](malea/blob/master/src/malea/ma_fsa.clj):

```clojure
(ns foo
  (:use malea.ma-fsa))

;; Let `dictionary` be some list of terms.
(let [dawg (ma-fsa dictionary)]
  (accepts? dawg "bar")) ;-> Whether "bar" was among the terms of the dictionary
```

## Testing

You may run the tests via:

```
$ lein midje
```

## License

Copyright © 2012 Dylon Edwards

Distributed under the [MIT License](http://www.opensource.org/licenses/mit-license.php).
