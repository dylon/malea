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

;; TODO: Implement the deletion of words from the DFA.  This can be done in the
;; following fashion:
;;
;;   Transition along the DFA until the final state is encountered that
;;   corresponds to the last character of the word being deleted.  If it has no
;;   outgoing edges, delete it and all those backtracked to the root, until
;;   either another final state is encountered (for a substring) or a state
;;   having two or more outgoing edges is found.  In either case, stop the
;;   deletion but remove the label corresponding to the character of the deleted
;;   word from the state's list of outgoing edges.  In case the algorithm is
;;   able to backtrack to the root, delete the label corresponding to the first
;;   character of the deleted word from the list of outgoing edges from the
;;   root, but do not delete the root.  

;; TODO: Implement a method by which new terms may be inserted into the DFA in
;; an online fashion.

;; TODO: Implement a method by which a term may be updated, efficiently.  Rather
;; than deleting the old term and inserting the updated one, find their longest
;; common prefix, and delete everything from the old term from there; likewise,
;; insert everything corresponding to the new term, beginning at that state. 

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

(deftype MaFsaState [^:unsynchronized-mutable final
                     ^:unsynchronized-mutable transitions]

  IMaFsaState

  (transitions [this]
    transitions)

  (labels [this]
    (keys transitions))

  (transition [this label]
    (transitions label))

  (final? [this]
    final)

  (add-transition! [this label state]
    "Map this state to another using a distinct label for the directed edge."
    (set! transitions (assoc transitions label state))
    this)

  (finalize! [this]
    "Flag this state as being accepting."
    (set! final true)
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

  (start-state [this]
               "The start-state of this MA-FSA")

  (insert [this word]
          "Inserts a new word into this MA-FSA.")

  (finish! [this]
           "Minimizes all the states of this MA-FSA.  This is called after all
           the words have been added.")

  (minimize-later! [this transition]
                   "Adds `transition` to the list of transitions to minimize")

  (minimize! [this lower-bound]
             "Minimizes the unchecked states of this MA-FSA until the size of
             the stack of unchecked states is no greater than `lower-bound`.")

  (accepts? [this word]
            "Determines whether `word` is accepted by this MA-FSA."))

(deftype MaFsa [^:unsynchronized-mutable previous-word
                start-state ;; immutable
                ^:unsynchronized-mutable unchecked-states
                ^:unsynchronized-mutable minimized-states]

  IMaFsa

  (start-state [this]
    start-state)

  (insert [this word]
    (let [lower-bound (longest-common-prefix-length word previous-word)
          word-length (count word)]
      (minimize! this lower-bound)
      (finalize!
        (loop [index lower-bound
               state (if (empty? unchecked-states)
                       start-state ;-> new initial character
                       (last (peek unchecked-states)))]
          (if-not (< index word-length)
            state ;-> return the state to finalize!
            (let [label (nth word index)
                  next-state (ma-fsa-state)]
              (add-transition! state label next-state)
              (minimize-later! this [state label next-state])
              (recur (inc index) next-state)))))
      (set! previous-word word)
      this))

  (finish! [this]
    (minimize! this 0))

  (minimize-later! [this transition]
    (set! unchecked-states
          (conj unchecked-states transition)))

  (minimize! [this lower-bound]
    (while (> (count unchecked-states) lower-bound)
      (let [[state label next-state] (peek unchecked-states)]
        (set! unchecked-states (pop unchecked-states))
        (if-let [minimized-state (minimized-states next-state)]
          (add-transition! state label minimized-state)
          (set! minimized-states
                (conj minimized-states next-state)))))
      this)

  (accepts? [this word]
    (let [word-length (count word)]
      (loop [state start-state
             index 0]
        (if (nil? state)
          false ;-> the previous state did not have a transition for the label
          (if (= index word-length)
            (final? state) ;-> return whether the current state is accepting
            (let [label (nth word index)]
              (recur (transition state label) (inc index)))))))))

(defn ma-fsa-state
  "Constructs a new MA-FSA state according to the following parameters:

  1. `final` := Whether the state is accepting.
  2. `transitions` := A mapping of labels to states from the new state."

   ([final transitions]
    (MaFsaState. final transitions))

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
         previous-word ""
         start-state (ma-fsa-state)
         unchecked-states '()
         minimized-states #{}
         dawg (MaFsa.
                previous-word start-state unchecked-states minimized-states)]
     (finish!
       (reduce #(insert %1 %2) dawg dictionary))))

  ([dictionary]
   (ma-fsa dictionary false)))

