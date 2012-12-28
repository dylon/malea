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

;; ## Introduction
;;
;; The algorithm for imitating Levenshtein automata was taken from the following
;; journal article:
;; 
;;     \@ARTICLE{Schulz02faststring,
;;         author = {Klaus Schulz and Stoyan Mihov},
;;         title = {Fast String Correction
;;                  with Levenshtein-Automata},
;;         journal = {INTERNATIONAL JOURNAL OF DOCUMENT
;;                    ANALYSIS AND RECOGNITION},
;;         year = {2002},
;;         volume = {5},
;;         pages = {67--85}
;;     }
;; 
;; As well, this Master Thesis helped me understand its concepts:
;; 
;;   * www.fmi.uni-sofia.bg/fmi/logic/theses/mitankin-en.pdf
;; 
;; The supervisor of the student who submitted the thesis was one of the
;; authors of the journal article, above.
;; 
;; The algorithm for constructing a DAWG (Direct Acyclic Word Graph) from the
;; input dictionary of words (DAWGs are otherwise known as an MA-FSA, or Minimal
;; Acyclic Finite-State Automata), was taken and modified from the following
;; blog from Steve Hanov:
;; 
;;   * http://stevehanov.ca/blog/index.php?id=115
;; 
;; The algorithm therein was taken from the following paper:
;; 
;;     \@MISC{Daciuk00incrementalconstruction,
;;       author = {Jan Daciuk
;;                 and Bruce W. Watson
;;                 and Richard E. Watson
;;                 and Stoyan Mihov},
;;       title = {Incremental Construction of Minimal
;;                Acyclic Finite-State Automata},
;;       year = {2000}
;;     }

(ns malea.levenshtein
  (:require [malea.ma-fsa :as ma-fsa]))

;; Levenshtein distance, including insertions, deletions, and substitutions as
;; elementary edit operations. This is the traditional edit distance metric.
(def ^:const STANDARD 2r01)

;; Levenshtein distance, including insertions, deletions, substitutions, and
;; transpositions as elementary edit operations. This is useful, primarily, in
;; typesetting applications.
(def ^:const TRANSPOSITION 2r10)

;; Levenshtein distance, including insertions, deletions, substitutions, merges,
;; and splits as elementary edit operations. This is useful, primarily, in OCR
;; applications.
(def ^:const MERGE-AND-SPLIT 2r11)

;; For a given vector collection, returns the element of `collection` at `index`
;; if `index` is bounded by the vector's length.  Otherwise, `nil` is returned.   
(defn get-nth [collection index]
  (if-not (< index (count collection)) nil
    (nth collection index)))

;; Syntactic sugar for negating a while loop.  Example usage:
;;
;;     (while-not predicate?
;;       (expression-1)
;;       (expression-2)
;;       ...)
(defmacro while-not [predicate? & expressions]
  `(while (not ~predicate?)
     ~@expressions))

;; If `value` is non-zero, return it.  Otherwise, return `nil` for a falsy
;; value (this is useful for disjunctions and is inspired by Ruby's `nonzero?`
;; function).
(defn nonzero? [value]
  (if (zero? value) nil value))

;; Inserts `value` into `collection` at `index`.
(defn insert [collection value index]
  (concat (take index collection)
          (cons value (drop index collection))))

;; Drops the element at `index` from `collection`. 
(defn drop-index [collection index]
  (concat (take index collection)
          (drop (inc index) collection)))

;; Returns the absolute value of `value`.
(definline abs [value]
  `(if (neg? ~value) (- ~value) ~value))

;; For a given `state` and word length `w`, determine the minimum edit distance
;; of that `state`.
(defmacro ->distance [state w]
   `(let [[i# e#] ~state]
      (+ e# (- ~w i#))))

;; ## ILevenshteinTransducer
;;
;; Protocol which must be implemented by every Levenshtein Transducer.
;;
;; ### (dictionary [this])
;;
;; DFA dictionary containing the terms that may be queried against.
;;
;; ### (index-of [this characteristic-vector k i])
;;
;; Returns the first index corresponding to a `true` value within the
;; characteristic vector, beginning at offset `i` and ending at `i + k`.  The
;; index begins at `0` for `i`, and ends at `k` for `i + k`.  If no element
;; corresponds to a `true` value within the range `i` to `i + k`, the value `-1`
;; is returned.
;;    
;; 1. `characteristic-vector` Vector of booleans corresponding to the characters
;; within the query term -- at the given offset `i` of the given length
;; `i + k` -- that match the edge-label of interest.
;; 2. `k` Number of elements to examine.
;; 3. `i` Offset of where to begin traversing the characteristic vector,
;; which must be no greater than k.
;;
;; ### (bisect-error-right [this state e l])
;;
;; Given two positions `[i,e]` and `[j,f]`, for `[i,e]` to subsume `[j,f]`, it
;; must be the case that `e < f`.  Therefore, I can remove a redundant check
;; for `e < f` within the `subsumes?` method by finding the first index that
;; contains a position having an error greater than the current one (assuming
;; that the positions are sorted in ascending order, according to error).
;;
;; ### (transition-for-position [this n])
;;
;; Returns a position transition function, which maps positions into states
;; according to the specified algorithm.
;;   
;; 1. `n` Maximum allowed edit distance.
;;   
;; A position transition function of the following signature is returned; it is
;; the heart of its respective algorithm:
;;   
;;     (fn [position characteristic-vector offset])
;;
;; The first parameter corresponds to the current position, the second
;; corresponds to a characteristic vector at the minimal boundary of the current
;; state, and the third is the offset with which to relabel the boundary of the
;; current position.
;;
;; ### (subsumes? [this i,e j,f] [this i,e,s j,f,t n] [this i,e,s j,f,t])
;;
;; Accepts the parameters of two positions, and determines whether the first
;; subsumes the second.
;;  
;; Within each algorithm-specific implementation, you will notice that I have
;; left off the essential check for (< e f).  I have derived an algorithm that
;; ensures the constraint is enforced, and I therefore do not need to check that
;; it is so.  For more details, please see my comments about
;; `bisect-error-right` and how I am using it in `unsubsume-for`.
;;
;; ### (unsubsume-for [this n])
;;
;; Returns a function that removes all the subsumed positions (unsubsumes) from
;; a state vector.  It is necessary that the state vector has had its positions
;; sorted in a particular fashion, as defined within `insert-for-subsumption`.
;;
;; TODO: Experiment with whether it is faster to perform a linear scan rather
;; than invoking a method call, to find the value for `n`.
;;
;; ### (insert-for-subsumption [this state' next-state])
;;
;; Insertion-sort according to error first, then boundary (both ascending).
;; While sorting the elements, remove any duplicates.
;;
;; ### (minimum-distance [this state w])
;;
;; The distance of each position in a state can be defined as follows, where `w`
;; is the length of the query term, `i` is the boundary of the current position,
;; and `e` is the current position's error:
;;
;;     distance = w - i + e
;;
;; It is characteristic of the generated states that `i <= w`.  Therefore,
;; `w - i` provides the number of characters remaining to be inserted before the
;; two terms are of equal lengths.  Because each insertion has a weight of `1`,
;; the number of characters remaining increases the position's current error by
;; the same number (of characters remaining).  This is the intuition behind how
;; I derived the cumulative error of the position of interest.
;;
;; For every accepting position, it must be the case that `w - i <= n - e`,
;; where `n` is the maximum allowed edit distance.  It follows directly that the
;; distance of every accepted position must be no more than `n`:
;;
;;     (w - i <= n - e) <=> (w - i + e <= n) <=> (distance <= n)
;;
;; The Levenshtein distance between any two terms is defined as the minimum edit
;; distance between them.  Therefore, iterate over each position in an accepting
;; state, and take the minimum distance among all its accepting positions as the
;; corresponding Levenshtein distance.  This is the intuition behind how I
;; derieved the minimum distance of the state of interest.
(defprotocol ILevenshteinTransducer
  (dictionary [this])
  (index-of [this characteristic-vector k i])
  (bisect-error-right [this state e l])
  (start-state [this])
  (characteristic-vector [this x term k i]) 
  (transition-for-position [this n])
  (bisect-left [this state position])
  (subsumes? [this i,e j,f] [this i,e,s j,f,t n] [this i,e,s j,f,t])
  (unsubsume-for [this n])
  (insert-for-subsumption [this state' next-state])
  (sort-for-transition [this state])
  (final? [this state w n])
  (minimum-distance [this state w])
  (transition-for-state [this n])
  (transduce [this term n]))

;; ## (->LevenshteinTransducer [identifier & body])
;;
;; Adds shared methods to the `deftype` of a Levenshtein transduction algorithm.
;;
;; 1. `identifier` Name to supply the `deftype` for the transducer.
;; 2. `body` Remaining implementation of the `ILevenshteinTransducer` protocol.
(defmacro ->LevenshteinTransducer [identifier & body]
  `(deftype ~identifier [dictionary#]
     ILevenshteinTransducer
 
     (dictionary [this]
       dictionary#)
 
     (index-of [this# characteristic-vector# k# i#]
       (loop [j# 0]
         (if (< j# k#)
           (if (nth characteristic-vector# (+ i# j#))
             j# ;-> return j 
             (recur (inc j#)))
           -1)))
 
     (bisect-error-right [this# state# e# l#]
       (loop [l# l#
              u# (count state#)]
         (if-not (< l# u#) l#
           (let [i# (bit-shift-right (+ l# u#) 1)]
             (if (< e# (nth (nth state# i#) 1))
               (recur l# i#)
               (recur (inc i#) u#))))))

    (characteristic-vector [this# x# term# k# i#]
      (vec 
        (for [j# (range k#)]
          (= x# (nth term# (+ i# j#))))))

     ~@body 

    (transition-for-state [this# n#]
      (let [transition# (transition-for-position this# n#)
            unsubsume# (unsubsume-for this# n#)]
        (fn [state# characteristic-vector#]
          (let [offset# (first (first state#))
                state'# (atom [])]
            (doseq [position# state#]
              (when-let [next-state#
                         (transition# position# characteristic-vector# offset#)]
                (insert-for-subsumption this# state'# next-state#)))
            (unsubsume# state'#)
            (if (empty? @state'#) nil
              (sort-for-transition this# @state'#))))))
 
     (transduce [this# term# n#]
       (let [w# (count term#)
             transition# (transition-for-state this# n#)
             matches# (atom '())
             stack#
              (atom
                (list
                  ["" (ma-fsa/start-state dictionary#) (start-state this#)]))]
         (while-not (empty? @stack#)
           (let [[V# q_D# M#] (peek @stack#)
                 i# (first (first M#))
                 a# (+ 1 (* 2 n#))
                 b# (- w# i#)
                 k# (min a# b#)]
             (swap! stack# pop)
             (doseq [[x# next-q_D#] (ma-fsa/transitions q_D#)]
               (let [characteristic-vector#
                      (characteristic-vector this# x# term# k# i#)
                     next-M# (transition# M# characteristic-vector#)]
                 (when-not (nil? next-M#)
                   (let [next-V# (str V# x#)]
                     (swap! stack# conj [next-V# next-q_D# next-M#])
                     (when (ma-fsa/final? next-q_D#)
                       (let [distance# (minimum-distance this# next-M# w#)]
                         (when (<= distance# n#)
                           (swap! matches# conj [next-V# distance#]))))))))))
         @matches#))
     
     clojure.lang.IFn
 
     (invoke [this# term# n#]
       (transduce this# term# n#)) 

     (invoke [this# term#]
       (this# term# 2))))

(->LevenshteinTransducer LevenshteinTransducerWithStandardMetric

  (start-state [this]
    [[0 0]])

  (transition-for-position [this n]
    (fn [[i e] characteristic-vector offset]
      (let [h (- i offset)
            w (count characteristic-vector)]
        (if (< e n)
          (if (<= h (- w 2))
            (let [a (+ 1 (- n e))
                  b (- w h)
                  k (min a b)
                  j (index-of this characteristic-vector k h)]
              (if (= j 0)
                [
                  [(+ i 1) e]
                ] 
                (if (> j 0)
                  [
                    [    i     (+ e 1)]
                    [(+ i 1  ) (+ e 1)]
                    [(+ i j 1) (+ e j)]
                  ] 
                  [
                    [   i    (+ e 1)]
                    [(+ i 1) (+ e 1)]
                  ])))
            (if (= h (- w 1))
              (if (nth characteristic-vector h)
                [
                  [(+ i 1) e]
                ]
                [
                  [   i    (+ e 1)]
                  [(+ i 1) (+ e 1)]
                ])
              [
                [i (+ e 1)]
              ]))
          (if (= e n)
            (if (<= h (- w 1))
              (if (nth characteristic-vector h)
                [
                  [(+ i 1) n] ;; I could have returned [(+ i 1) e], but (= e n)
                              ;; and I decided substituting n for e was clearer
                ] 
                nil)
              nil)
            nil)))))

  (bisect-left [this state [i e]]
    (loop [l 0
           u (count state)]
      (if-not (< l u) l ;-> return l
        (let [k (bit-shift-right (+ l u) 1)
              p (nth state k)]
          (if (pos?
                (or (nonzero? (- e (nth p 1)))
                    (- i (nth p 0))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e j,f]
    ;; (e < f) && abs(j - i) <= (f - e)
    (<= (abs (- j i)) (- f e)))

  (unsubsume-for [this n]
    (fn [state]
      (loop [m 0]
        (when (< m (count @state))
          (let [[i e] (nth @state m)]
            (loop [n (bisect-error-right this @state e m)]
              (when (< n (count @state))
                (let [[j f] (nth @state n)]
                  (if (subsumes? this i,e j,f)
                    (do
                      (swap! state drop-index n)
                      (recur n))
                    (recur (inc n))))))
            (recur (inc m)))))
      (swap! state vec)))

  (insert-for-subsumption [this state' next-state]
    (doseq [position next-state]
      (let [index (bisect-left this @state' position)]
        (if-let [current-position (get-nth @state' index)]
          (when (or (not= (nth current-position 0) (nth position 0))
                    (not= (nth current-position 1) (nth position 1)))
            (swap! state' #(insert % position index)))
          (swap! state' #(concat %1 [position])))))
    (swap! state' vec))

  (sort-for-transition [this state]
    (vec 
      (sort #(or (nonzero? (- (nth %1 0) (nth %2 0)))
                 (- (nth %1 1) (nth %2 1))) state)))

  (final? [this state w n]
    (loop [index 0]
      (if-not (< index (count state)) false
        (let [[i e] (nth state index)]
          (if (<= (- w i) (- n e)) true
            (recur (inc index)))))))

  (minimum-distance [this state w]
    (loop [minimum-distance Double/POSITIVE_INFINITY
           index 0]
      (if-not (< index (count state)) minimum-distance
        (let [[i e] (nth state index)
              distance (+ e (- w i))]
          (recur (min minimum-distance distance) (inc index)))))))

(->LevenshteinTransducer LevenshteinTransducerExtendedWithTransposition

  (start-state [this]
    [[0 0 0]])

  (transition-for-position [this n]
    (fn [[i e t] characteristic-vector offset]
      (let [h (- i offset)
            w (count characteristic-vector)]
        (if (and (= e 0) (> n 0))
          (if (<= h (- w 2))
            (let [a (+ 1 (- n e))
                  b (- w h)
                  k (min a b)
                  j (index-of this characteristic-vector k h)]
              (if (= j 0)
                [
                  [(+ i 1) 0 0]
                ] 
                (if (= j 1)
                  [
                    [   i    1 0]
                    [   i    1 1] ;; t-position
                    [(+ i 1) 1 0]
                    [(+ i 2) 1 0] ;; was [(+ i j 1) j 0], but (= j 1), so I
                                  ;; simplified it:
                                  ;; (= (+ i j 1) (+ i 1 1) (+ i 2))
                  ] 
                  (if (> j 1) ;; (= j -1) is handled below 
                    [
                      [    i     1 0]
                      [ (+ i 1)  1 0]
                      [(+ i j 1) j 0]
                    ]
                    [
                      [   i    1 0]
                      [(+ i 1) 1 0]
                    ]))))
            (if (= h (- w 1))
              (if (nth characteristic-vector h)
                [
                  [(+ i 1) 0 0]
                ] 
                [
                  [   i    1 0]
                  [(+ i 1) 1 0]
                ])
              [
                [i 1 0]
              ])) 
          (if (and (<= 1 e) (< e n))
            (if (<= h (- w 2))
              (if (= t 0) ;; [i e t] is not a t-position
                (let [a (+ 1 (- n e))
                      b (- w h)
                      k (min a b)
                      j (index-of this characteristic-vector k h)]
                  (if (= j 0)
                    [
                      [(+ i 1) e 0]
                    ] 
                    (if (= j 1)
                      [
                        [   i    (+ e 1) 0]
                        [   i    (+ e 1) 1] ;; t-position
                        [(+ i 1) (+ e 1) 0]
                        [(+ i 2) (+ e 1) 0] ;; was [(+ i j 1) (+ e j) 0], but
                                            ;; (= j 1), so I simplified it:
                                            ;; (= (+ i j 1) (+ i 1 1) (+ i 2))
                      ] 
                      (if (> j 1) ;; (= j -1) is handled below
                        [
                          [    i     (+ e 1) 0]
                          [ (+ i 1)  (+ e 1) 0]
                          [(+ i j 1) (+ e j) 0]
                        ] 
                        [
                          [   i    (+ e 1) 0]
                          [(+ i 1) (+ e 1) 0]
                        ]))))
                (if (nth characteristic-vector h)
                  [
                    [(+ i 2) e 0]
                  ] 
                  nil))
              (if (= h (- w 1))
                (if (nth characteristic-vector h)
                  [
                    [(+ i 1) e 0]
                  ] 
                  [
                    [   i    (+ e 1) 0]
                    [(+ i 1) (+ e 1) 0]
                  ]) 
                [ ;; (= h w)
                  [i (+ e 1) 0]
                ])) 
            (if (and (<= h (- w 1)) (= t 0))
              (if (nth characteristic-vector h)
                [
                  [(+ i 1) n 0]
                ] 
                nil) 
              (if (and (<= h (- w 2)) (= t 1)) ;; [i e t] is a t-position
                (if (nth characteristic-vector h)
                  [
                    [(+ i 2) n 0]
                  ] 
                  nil) 
                nil)))))))

  (bisect-left [this state [i e x]]
    (loop [l 0
           u (count state)]
      (if-not (< l u) l ;-> return l
        (let [k (bit-shift-right (+ l u) 1)
              p (nth state k)]
          (if (pos?
                (or (nonzero? (- e (nth p 1)))
                    (nonzero? (- i (nth p 0)))
                    (- x (nth p 2))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e,s j,f,t n]
    (if (= s 1)
      (if (= t 1)
        ;; (e < f) && (i == j) 
        (= i j)

        ;; (e < f == n) && (i == j) 
        (and (= f n) (= i j)))

      (if (= t 1)
        ;; NOTE: This is how I derived what follows:
        ;;
        ;;     abs(j - (i - 1)) = abs(j - i + 1) = abs(j - i) + 1
        ;;
        ;; (e < f) && abs(j - (i - 1)) <= (f - e)
        (<= (+ 1 (abs (- j i)))
            (- f e))

        ;; (e < f) && abs(j - i) <= (f - e)
        (<= (abs (- j i))
            (- f e)))))

  (unsubsume-for [this n]
    (fn [state]
      (loop [m 0]
        (when (< m (count @state))
          (let [[i e s] (nth @state m)]
            (loop [n (bisect-error-right this @state e m)]
              (when (< n (count @state))
                (let [[j f t] (nth @state n)]
                  (if (subsumes? this i,e,s j,f,t n)
                    (do
                      (swap! state drop-index n)
                      (recur n))
                    (recur (inc n))))))
            (recur (inc m)))))
      (swap! state vec)))

  (insert-for-subsumption [this state' next-state]
    (doseq [position next-state]
      (let [index (bisect-left this @state' position)]
        (if-let [current-position (get-nth @state' index)]
          (when (or (not= (nth current-position 0) (nth position 0))
                    (not= (nth current-position 1) (nth position 1))
                    (not= (nth current-position 2) (nth position 2)))
            (swap! state' #(insert % position index)))
          (swap! state' #(concat %1 [position])))))
    (swap! state' vec))

  (sort-for-transition [this state]
    (vec 
      (sort #(or (nonzero? (- (nth %1 0) (nth %2 0)))
                 (nonzero? (- (nth %1 1) (nth %2 1)))
                 (- (nth %1 2) (nth %2 2))) state)))

  (final? [this state w n]
    (loop [index 0]
      (if-not (< index (count state)) false
        (let [[i e x] (nth state index)]
          (if (and (not= x 1)
                   (<= (- w i) (- n e))) true
            (recur (inc index)))))))

  (minimum-distance [this state w]
    (loop [minimum-distance (->distance (nth state 0) w)
           index 1]
      (if-not (< index (count state)) minimum-distance
        (let [x (nth (nth state index) 2)
              distance (->distance (nth state index) w)
              minimum-distance (if (and (not= x 1) (< distance minimum-distance))
                                 distance  
                                 minimum-distance)]
          (recur minimum-distance (inc index)))))))

(->LevenshteinTransducer LevenshteinTransducerExtendedWithMergeAndSplit

  (start-state [this]
    [[0 0 0]])

  (transition-for-position [this n]
    (fn [[i e s] characteristic-vector offset]
      (let [h (- i offset)
            w (count characteristic-vector)]
        (if (and (= e 0) (> n 0))
          (if (<= h (- w 2))
            (if (nth characteristic-vector h)
              [
                [(+ i 1) e 0]
              ] 
              [
                [   i    (+ e 1) 0]
                [   i    (+ e 1) 1] ;; s-position
                [(+ i 1) (+ e 1) 0]
                [(+ i 2) (+ e 1) 0]
              ])
            (if (= h (- w 1))
              (if (nth characteristic-vector h)
                [
                  [(+ i 1) e 0]
                ]
                [
                  [   i    (+ e 1) 0]
                  [   i    (+ e 1) 1] ;; s-position
                  [(+ i 1) (+ e 1) 0]
                ])
              [ ;; (= h w)
                [i (+ e 1) 0]
              ]))
          (if (< e n)
            (if (<= h (- w 2))
              (if (= s 0)
                (if (nth characteristic-vector h)
                  [
                    [(+ i 1) e 0]
                  ]
                  [
                    [   i    (+ e 1) 0]
                    [   i    (+ e 1) 1] ;; s-position
                    [(+ i 1) (+ e 1) 0]
                    [(+ i 2) (+ e 1) 0]
                  ])
                [ ;; [i e s] is an s-position
                  [(+ i 1) e 0]
                ])
              (if (= h (- w 1))
                (if (= s 0)
                  (if (nth characteristic-vector h)
                    [
                      [(+ i 1) e 0]
                    ]
                    [
                      [   i    (+ e 1) 0]
                      [   i    (+ e 1) 1] ;; s-position
                      [(+ i 1) (+ e 1) 0]
                    ])
                  [ ;; [i e s] is an s-position
                    [(+ i 1) e 0]
                  ])
                [ ;; (= h w)
                  [i (+ e 1) 0]
                ]))
            (if (<= h (- w 1))
              (if (= s 0)
                (if (nth characteristic-vector h)
                  [
                    [(+ i 1) n 0]
                  ]
                  nil)
                [ ;; [i e s] is an s-position
                  [(+ i 1) e 0]
                ])
              nil))))))

  (bisect-left [this state [i e x]]
    (loop [l 0
           u (count state)]
      (if-not (< l u) l ;-> return l
        (let [k (bit-shift-right (+ l u) 1)
              p (nth state k)]
          (if (pos?
                (or (nonzero? (- e (nth p 1)))
                    (nonzero? (- i (nth p 0)))
                    (- x (nth p 2))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e,s j,f,t]
    (if (and (= s 1) (= t 0)) false
      ;; (e < f) && abs(j - i) <= (f - e)
      (<= (abs (- j i))
          (- f e))))

  (unsubsume-for [this n]
    (fn [state]
      (loop [m 0]
        (when (< m (count @state))
          (let [[i e s] (nth @state m)]
            (loop [n (bisect-error-right this @state e m)]
              (when (< n (count @state))
                (let [[j f t] (nth @state n)]
                  (if (subsumes? this i,e,s j,f,t)
                    (do
                      (swap! state drop-index n)
                      (recur n))
                    (recur (inc n))))))
            (recur (inc m)))))
      (swap! state vec)))

  (insert-for-subsumption [this state' next-state]
    (doseq [position next-state]
      (let [index (bisect-left this @state' position)]
        (if-let [current-position (get-nth @state' index)]
          (when (or (not= (nth current-position 0) (nth position 0))
                    (not= (nth current-position 1) (nth position 1))
                    (not= (nth current-position 2) (nth position 2)))
            (swap! state' #(insert % position index)))
          (swap! state' #(concat %1 [position])))))
    (swap! state' vec))

  (sort-for-transition [this state]
    (vec 
      (sort #(or (nonzero? (- (nth %1 0) (nth %2 0)))
                 (nonzero? (- (nth %1 1) (nth %2 1)))
                 (- (nth %1 2) (nth %2 2))) state)))

  (final? [this state w n]
    (loop [index 0]
      (if-not (< index (count state)) false
        (let [[i e x] (nth state index)]
          (if (and (not= x 1)
                   (<= (- w i) (- n e))) true
            (recur (inc index)))))))

  (minimum-distance [this state w]
    (loop [minimum-distance (->distance (nth state 0) w)
           index 1]
      (if-not (< index (count state)) minimum-distance
        (let [x (nth (nth state index) 2)
              distance (->distance (nth state index) w)
              minimum-distance (if (and (not= x 1) (< distance minimum-distance))
                                 distance  
                                 minimum-distance)]
          (recur minimum-distance (inc index)))))))


(defmulti transducer
  (fn
    ([dfa-dictionary algorithm]
     algorithm)
    ([dfa-dictionary]
     STANDARD)))

(defmethod transducer STANDARD [dfa-dictionary & params]
  (LevenshteinTransducerWithStandardMetric. dfa-dictionary))

(defmethod transducer TRANSPOSITION [dfa-dictionary & params]
  (LevenshteinTransducerExtendedWithTransposition. dfa-dictionary))

(defmethod transducer MERGE-AND-SPLIT [dfa-dictionary & params]
  (LevenshteinTransducerExtendedWithMergeAndSplit. dfa-dictionary))

(defn transducer-from-list
  ([dictionary sorted algorithm]
   (let [dfa-dictionary (ma-fsa/ma-fsa dictionary sorted)]
     (transducer dfa-dictionary algorithm)))
  ([dictionary sorted]
   (transducer-from-list dictionary sorted STANDARD))
  ([dictionary]
   (transducer-from-list dictionary false)))
