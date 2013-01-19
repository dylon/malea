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

### VimClojure

I prefer Vim over Emacs, but you may use whatever editor you want. For my
purposes, though, I am going to document how to configure Vim for Clojure so I
do not forget. Please note that the following instructions are according to my
preferences: you may organize your files however you like.

```
$ cd ~/.vim/bundle/

$ git clone git://github.com/vim-scripts/VimClojure.git
Cloning into 'VimClojure'...
remote: Counting objects: 272, done.
remote: Compressing objects: 100% (140/140), done.
remote: Total 272 (delta 85), reused 259 (delta 74)
Receiving objects: 100% (272/272), 327.56 KiB | 600 KiB/s, done.
Resolving deltas: 100% (85/85), done.

$ cd /usr/local/share/

$ hg clone https://bitbucket.org/kotarak/vimclojure
warning: bitbucket.org certificate with fingerprint 24:9c:45:8b:9c:aa:ba:55:4e:01:6d:58:ff:e4:28:7d:2a:14:ae:3b not verified (check hostfingerprints or web.cacerts config setting)
destination directory: vimclojure
requesting all changes
adding changesets
adding manifests
adding file changes
added 654 changesets with 1243 changes to 343 files (+2 heads)
updating to branch default
80 files updated, 0 files merged, 0 files removed, 0 files unresolved

$ cd vimclojure/client/

$ make
Building ng client.  To build a Windows binary, type 'make ng.exe'
gcc -Wall -pedantic -s -O3  -o ng ngclient/ng.c

$ ln -vis "$PWD/ng" /usr/local/bin/
‘/usr/local/bin/ng’ -> ‘/usr/local/share/vimclojure/client/ng’
```

Add the following to your `~/.vimrc`:

```vim
let vimclojure#WantNailgun = 1
let g:vimclojure#ParenRainbow = 1
let g:vimclojure#DynamicHighlighting = 1
let vimclojure#SplitPos = "bottom"
let g:vimclojure#HighlightBuiltins = 1
```

I assume you are using Leiningen 2.  As such, add the following to your
`~/.lein/profiles.clj`:

```clojure
{:user {:dependencies [[vimclojure/server "2.3.6"]
											 [lein-tarsier "0.9.4"]]}}
```

After you run `$ lein deps`, initialize the NailGun server from the root,
project directory of `malea` as follows:

```
$ lein vimclojure
Starting VimClojure server on localhost.localdomain, port 2113
Happy hacking!
```

Now, you should be good to go.

### Dependencies

Make sure your dependencies are up-to-date (this will register the local jars,
as well):

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

Occasionally, `ragtime` skips a migration
(e.g. `migrations/20130105225711329_create_n_grams.up.sql`).  If that happens, a
temporary workaround will be to run the migration file manually.  It sucks, I
know, but you are a big kid now.  Life is not a fair.  Life sucks, deal with it.
When life gives you a lemon, make lemonade.  Blah, blah, blah ...

```
$ psql -d malea -U males < migrations/20130105225711329_create_n_grams.up.sql
NOTICE:  CREATE TABLE will create implicit sequence "n_grams_id_seq" for serial column "n_grams.id"
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "n_grams_pkey" for table "n_grams"
CREATE TABLE

$ psql -d malea_test -U males < migrations/20130105225711329_create_n_grams.up.sql
NOTICE:  CREATE TABLE will create implicit sequence "n_grams_id_seq" for serial column "n_grams.id"
NOTICE:  CREATE TABLE / PRIMARY KEY will create implicit index "n_grams_pkey" for table "n_grams"
CREATE TABLE
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
