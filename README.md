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

Create the `malea` and `malea_test` databases, and the
`malea` user:

```
$ createuser malea
$ createdb malea -E UTF8
$ psql -d malea
psql (9.2.2)
Type "help" for help.

malea=# REVOKE connect ON DATABASE malea FROM PUBLIC;
REVOKE
malea=# GRANT connect ON DATABASE malea TO malea;
GRANT
malea=> \q
$ createdb malea_test -E UTF8
$ psql -d malea_test
psql (9.2.2)
Type "help" for help.

malea_test=# REVOKE connect ON DATABASE malea_test FROM PUBLIC;
REVOKE
malea_test=# GRANT connect ON DATABASE malea_test TO malea;
GRANT
malea_test=> \q
```

Run the migrations:

```
$ lein ragtime migrate
$ lein with-profile test ragtime migrate
```

### Wikipedia

Download the latest English Wikipedia dump:

```
$ wget -c http://download.wikimedia.org/enwiki/latest/enwiki-latest-pages-articles.xml.bz2 -P resources/wikipedia/
$ cd resources/wikipedia/
$ bunzip2 enwiki-latest-pages-articles.xml.bz2
$ cd -
```

### OpenNLP

Download the English-based, OpenNLP models from the following locations, and
place them into `resources/opennlp/models/`:

1. [Tokenizer](http://opennlp.sourceforge.net/models-1.5/en-token.bin "Trained on opennlp training data.")
	- `$ wget -c http://opennlp.sourceforge.net/models-1.5/en-token.bin -P resources/opennlp/models/`
2. [Sentence Detector](http://opennlp.sourceforge.net/models-1.5/en-sent.bin "Trained on opennlp training data.")
	- `$ wget -c http://opennlp.sourceforge.net/models-1.5/en-sent.bin -P resources/opennlp/models/`

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
$ lein with-profile test midje
```

## Auxiliary

### Generate Task

Included is a Leiningen task to generat various types of files.

#### Migration

Generates a new migration, joining all parameters, after the command, into a
single name:

```
$ lein generate migration create bigrams
Generated migration: migrations/20121231153012135_create_bigrams.up.sql
Generated migration: migrations/20121231153012135_create_bigrams.down.sql
```

#### Source

Generates a new source file, scoped according to the `malea` namespace, and a
corresponding test file (unless specified otherwise):

```
$ lein generate source wikipedia
Generated source file: src/clojure/malea/wikipedia.clj
Generated test file: test/malea/test/wikipedia.clj
```

If you only want to create a source file (without a corresponding test file):

```
$ lein generate source --no-test baz
Generated source file: src/clojure/malea/baz.clj
```

#### Test

Generates a new test file, scoped according to the `malea` namespace:

```
$ lein generate test foo bar
Generated test file: test/malea/test/foo/bar.clj
```

## License

Copyright © 2012 Dylon Edwards

Distributed under the [MIT License](http://www.opensource.org/licenses/mit-license.php).
