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

;; # Minimal Acyclic Finite-State Automata (MA-FSA)
;; A minimal acyclic finite-state automaton (MA-FSA), otherwise known in
;; Information Retrieval, Natural Language Processing, and related fields as a
;; directed acyclic word graph (DAWG), stores a set of words in a
;; space-efficient manner.  The primary purpose of an MA-FSA is to facilitate
;; extremely fast lookups over a large dictionary.  Because the dictionary is
;; compressed, one should prefer an MA-FSA over a Trie for very large
;; dictionaries (unless one does not mind running out of memory).
;;
;; The algorithm was taken and modified from the following sources:
;;  - Incremental Construction of Minimal Acyclic Finite-State Automata (2000)
;;    - Jan Daciuk
;;    - Stoyan Mihov
;;    - Bruce W. Watson
;;    - Richard E. Watson
;;  - http://stevehanov.ca/blog/index.php?id=115
;;    - Steve Hanov

(ns malea.ma-fsa)

(declare ma-fsa ma-fsa-state)

(defn- longest-common-prefix-length [word-1 word-2]
  "Compares two words, and returns the length of their longest common prefix."
  (let [upper-bound (min (count word-1) (count word-2))]
    (loop [index 0]
      (if-not
        (and
          (< index upper-bound)
          (= (nth word-1 index)
             (nth word-2 index)))
        index
        (recur (inc index))))))

(defmacro let! [atomic-value new-value]
  `(compare-and-set! ~atomic-value @~atomic-value ~new-value))

;; ## MA-FSA State
;; The state of a minimal acyclic finite-state automaton.  It has a list of
;; transitions to other states.  States are equivalent if they have identical
;; transitions, and each identical transition leads to identical states.
;;
;; It maintains state using the following parameters:
;;
;; 1. `:final` := Specifies whether this is an accepting state.
;;   - Initialized, by default, to false.
;; 2. `:transitions` := A mapping of labels (characters) to transition states.
;;   - Initialized to an empty map.

(defprotocol IMaFsaState

  (transitions [this]
               "Mapping of transitions from this state.")

  (labels [this]
          "Labels corresponding to the edges transitioning from this state.")

  (transition [this label]
              "The transition from this state mapped to by `label`.  If no such
              transition exists, then `nil` is returned.")

  (final? [this]
          "Whether this state is accepting.")

  (add-transition! [this label state]
                   "Adds a new transition to this state having the given
                   `label`.  If such a transition already exists, it is replaced
                   by `state`.")

  (finalize! [this]
             "Flags this state as being accepting."))

(defrecord MaFsaState [final transitions]

  IMaFsaState

  (transitions [this]
    (deref (:transitions this)))

  (labels [this]
    (keys (deref (:transitions this))))

  (transition [this label]
    ((deref (:transitions this)) label))

  (final? [this]
    (deref (:final this)))

  (add-transition! [this label state]
    "Map this state to another using a distinct label for the directed edge."
    (swap! (:transitions this) assoc label state)
    this)

  (finalize! [this]
    "Flag this state as being accepting."
    (compare-and-set! (:final this) false true)
    this))

;; ## MA-FSA
;; Uses the following parameters to maintain state:
;;
;; 1. `:previous-word` :=  Specifies the most recently inerserted word.
;;   - Initialized, by default, to the empty string.
;; 2. `:start-state` :=  Each word that is inserted into the MA-FSA will have its
;; first character anchored here.
;;   - Initialized, by default, to an MA-FSA state having no transitions.
;; 3. `:unchecked-states` := List of states that have not been checked for
;; duplication.
;;   - Initialized, by default, to an empty list.
;; 4. `:minimized-states` := Distinct states that have been checked for
;; duplication.
;;   - Initialized, by default, to an empty set.
;;
;; The current algorithm for constructing an MA-FSA requires that the words be
;; inserted in lexicographically-increasing order.  If this invariant is not
;; maintained, then the resulting MA-FSA may not reflect accurately on the
;; initial dictionary.

(defprotocol IMaFsa

  (insert [this word]
          "Inserts a new word into this MA-FSA.")

  (finish! [this]
           "Minimizes all the states of this MA-FSA.  This is called after all
           the words have been added.")

  (minimize! [this lower-bound]
             "Minimizes the unchecked states of this MA-FSA until the size of
             the stack of unchecked states is no greater than `lower-bound`.")

  (accepts? [this word]
            "Determines whether `word` is accepted by this MA-FSA."))

(defrecord MaFsa [previous-word start-state unchecked-states minimized-states]

  IMaFsa

  (insert [this word]
    (let [previous-word (:previous-word this)
          lower-bound (longest-common-prefix-length word @previous-word)]
      (minimize! this lower-bound)
      (let [unchecked-states (:unchecked-states this)
            state (atom (if (empty? @unchecked-states)
                         (:start-state this)
                         (last (peek @unchecked-states))))]
        (doseq [index (range lower-bound (count word))]
          (let [label (nth word index)
                next-state (ma-fsa-state)]
            (add-transition! @state label next-state)
            (swap! unchecked-states conj [@state label next-state])
            (let! state next-state)))
        (finalize! @state)
        (let! previous-word word))
      this))

  (finish! [this]
    (minimize! this 0))

  (minimize! [this lower-bound]
    (let [minimized-states (:minimized-states this)
          unchecked-states (:unchecked-states this)]
      (while (> (count @unchecked-states) lower-bound)
        (let [[state label next-state] (peek @unchecked-states)]
          (swap! unchecked-states pop)
          (if-let [minimized-state (@minimized-states next-state)]
            (add-transition! state label minimized-state)
            (swap! minimized-states conj next-state))))
      this))

  (accepts? [this word]
    (let [word-length (count word)]
      (loop [state (:start-state this)
             index 0]
        (if (nil? state)
          false
          (if (= index word-length)
            (final? state)
            (let [label (nth word index)]
              (recur (transition state label) (inc index)))))))))

(defn ma-fsa-state
  "Constructs a new MA-FSA state according to the following parameters:

  1. `final` := Whether the state is accepting.
  2. `transitions` := A mapping of labels to states from the new state."

   ([final transitions]
    (MaFsaState. (atom final) (atom transitions)))

   ([final]
    (ma-fsa-state final {}))

   ([]
    (ma-fsa-state false)))

(defn ma-fsa
  "Constructs a new MA-FSA according to the following parameters:

  1. `dictionary` := The list of words to insert into the MA-FSA.
  2. `sorted` := Whether `dictionary` has already been sorted.  If this is
  `false`, then `dictionary` is sorted before the MA-FSA is constructed (this is
  to maintain the invariant that all the words are inserted in
  lexicographically-increasing order)."

  ([dictionary sorted]
   (let [dictionary (if-not sorted (sort dictionary) dictionary)
         previous-word (atom "")
         start-state (ma-fsa-state)
         unchecked-states (atom '())
         minimized-states (atom #{})
         dawg (MaFsa.
                previous-word start-state unchecked-states minimized-states)]
     (finish!
       (reduce #(insert %1 %2) dawg dictionary))))

  ([dictionary]
   (ma-fsa dictionary false)))

