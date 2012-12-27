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

(ns malea.levenshtein
  (:require malea.ma-fsa))

(def ^:const STANDARD        2r01)
(def ^:const TRANSPOSITION   2r10)
(def ^:const MERGE-AND-SPLIT 2r11)

(defn get-nth [collection index]
  (if-not (< index (count collection)) nil
    (nth collection index)))

(defmacro while-not [predicate? & expressions]
  `(while (not ~predicate?)
     ~@expressions))

(defn nonzero? [value]
  (if (zero? value) nil value))

(defn insert [collection value index]
  (concat (take index collection)
          (cons value (drop index collection))))

(defn drop-index [collection index]
  (concat (take (dec index) collection)
          (drop index collection)))

(definline abs-int [value]
  `(if (neg? ~value) (- ~value) ~value))

(defmacro ->distance [state w]
   `(let [[i# e#] ~state]
      (+ e# (- ~w i#))))

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

(defmacro ->LevenshteinTransducer [identifier & algorithm]
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

     ~@algorithm

    (transition-for-state [this# n#]
      (let [transition# (transition-for-position this# n#)
            unsubsume# (unsubsume-for this# n#)]
        (fn [state# characteristic-vector#]
          (let [offset# (first (first state#))
                state'# (atom [])]
            (doseq [position# state#]
              (when-let [next-state# (transition# position# characteristic-vector# offset#)]
                (insert-for-subsumption this# state'# next-state#)))
            (unsubsume# state'#)
            (if (empty? @state'#) nil
              (sort-for-transition this# @state'#))))))
 
     (transduce [this# term# n#]
       (let [w# (count term#)
             transition# (transition-for-state this# n#)
             matches# (atom '())
             stack# (atom (list ["" (malea.ma-fsa/start-state dictionary#) (start-state this#)]))]
         (while-not (empty? @stack#)
           (let [[V# q_D# M#] (peek @stack#)
                 i# (first (first M#))
                 a# (+ 1 (* 2 n#))
                 b# (- w# i#)
                 k# (min a# b#)]
             (swap! stack# pop)
             (doseq [[x# next-q_D#] (malea.ma-fsa/transitions q_D#)]
               (let [characteristic-vector# (characteristic-vector this# x# term# k# i#)
                     next-M# (transition# M# characteristic-vector#)]
                 (when-not (nil? next-M#)
                   (let [next-V# (str V# x#)]
                     (swap! stack# conj [next-V# next-q_D# next-M#])
                     (when (malea.ma-fsa/final? next-q_D#)
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
                  [(+ i 1) n] ;-> I could have returned [(+ i 1) e], but (= e n) and I decided substituting n for e was clearer
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
          (if (or (pos? (- e (nth p 1)))
                  (pos? (- i (nth p 0))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e j,f]
    (<= (abs-int (- j i)) (- f e)))

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

;(deftype LevenshteinTransducerWithStandardMetric [dictionary]
   ;ILevenshteinTransducer

   ;(dictionary [this]
     ;dictionary)

   ;(index-of [this characteristic-vector k i]
     ;(loop [j 0]
       ;(if (< j k)
         ;(if (nth characteristic-vector (+ i j))
           ;j ;-> return j 
           ;(recur (inc j)))
         ;-1)))

   ;(bisect-error-right [this state e l]
     ;(loop [l l
            ;u (count state)]
       ;(if-not (< l u) l
         ;(let [i (bit-shift-right (+ l u) 1)]
           ;(if (< e (nth (nth state i) 1))
             ;(recur l i)
             ;(recur (inc i) u))))))

  ;(characteristic-vector [this x term k i]
    ;(vec 
      ;(for [j (range k)]
        ;(= x (nth term (+ i j))))))


  ;(start-state [this]
    ;[[0 0]])

  ;(transition-for-position [this n]
    ;(fn [[i e] characteristic-vector offset]
      ;(let [h (- i offset)
            ;w (count characteristic-vector)]
        ;(if (< e n)
          ;(if (<= h (- w 2))
            ;(let [a (+ 1 (- n e))
                  ;b (- w h)
                  ;k (min a b)
                  ;j (index-of this characteristic-vector k h)]
              ;(if (= j 0)
                ;[
                  ;[(+ i 1) e]
                ;] 
                ;(if (> j 0)
                  ;[
                    ;[    i     (+ e 1)]
                    ;[(+ i 1  ) (+ e 1)]
                    ;[(+ i j 1) (+ e j)]
                  ;] 
                  ;[
                    ;[   i    (+ e 1)]
                    ;[(+ i 1) (+ e 1)]
                  ;])))
            ;(if (= h (- w 1))
              ;(if (nth characteristic-vector h)
                ;[
                  ;[(+ i 1) e]
                ;]
                ;[
                  ;[   i    (+ e 1)]
                  ;[(+ i 1) (+ e 1)]
                ;])
              ;[
                ;[i (+ e 1)]
              ;]))
          ;(if (= e n)
            ;(if (<= h (- w 1))
              ;(if (nth characteristic-vector h)
                ;[
                  ;[(+ i 1) n] ;-> I could have returned [(+ i 1) e], but (= e n) and I decided substituting n for e was clearer
                ;] 
                ;nil)
              ;nil)
            ;nil)))))

  ;(bisect-left [this state [i e]]
    ;(loop [l 0
           ;u (count state)]
      ;(if-not (< l u) l ;-> return l
        ;(let [k (bit-shift-right (+ l u) 1)
              ;p (nth state k)]
          ;(if (or (pos? (- e (nth p 1)))
                  ;(pos? (- i (nth p 0))))
            ;(recur (inc k) u)
            ;(recur l k))))))

  ;(subsumes? [this i,e j,f]
    ;(<= (abs-int (- j i)) (- f e)))

  ;(unsubsume-for [this n]
    ;(fn [state]
      ;(loop [m 0]
        ;(when (< m (count @state))
          ;(let [[i e] (nth @state m)]
            ;(loop [n (bisect-error-right this @state e m)]
              ;(when (< n (count @state))
                ;(let [[j f] (nth @state n)]
                  ;(if (subsumes? this i,e j,f)
                    ;(do
                      ;(swap! state drop-index n)
                      ;(recur n))
                    ;(recur (inc n))))))
            ;(recur (inc m)))))
      ;(swap! state vec)))

  ;(insert-for-subsumption [this state' next-state]
    ;(doseq [position next-state]
      ;(let [index (bisect-left this @state' position)]
        ;(if-let [current-position (get-nth @state' index)]
          ;(when (or (not= (nth current-position 0) (nth position 0))
                    ;(not= (nth current-position 1) (nth position 1)))
            ;(swap! state' #(insert % position index)))
          ;(swap! state' #(concat %1 [position])))))
    ;(swap! state' vec))

  ;(sort-for-transition [this state]
    ;(vec 
      ;(sort #(or (nonzero? (- (nth %1 0) (nth %2 0)))
                 ;(- (nth %1 1) (nth %2 1))) state)))

  ;(final? [this state w n]
    ;(loop [index 0]
      ;(if-not (< index (count state)) false
        ;(let [[i e] (nth state index)]
          ;(if (<= (- w i) (- n e)) true
            ;(recur (inc index)))))))

  ;(transition-for-state [this n]
    ;(let [transition (transition-for-position this n)
          ;unsubsume (unsubsume-for this n)]
      ;(fn [state characteristic-vector]
        ;(let [offset (first (first state))
              ;state' (atom [])]
          ;(doseq [position state]
            ;(when-let [next-state (transition position characteristic-vector offset)]
              ;(insert-for-subsumption this state' next-state)))
          ;(unsubsume state')
          ;(if (empty? @state') nil
            ;(sort-for-transition this @state'))))))

   ;(minimum-distance [this state w]
     ;(loop [minimum-distance (->distance (nth state 0) w)
            ;index 1]
       ;(if-not (< index (count state)) minimum-distance
         ;(let [distance (->distance (nth state index) w)]
           ;(recur (min minimum-distance distance) (inc index))))))

   ;(transduce [this term n]
     ;(let [w (count term)
           ;transition (transition-for-state this n)
           ;matches (atom '())
           ;stack (atom (list ["" (malea.ma-fsa/start-state dictionary) (start-state this)]))]
       ;(while-not (empty? @stack)
         ;(let [[V q_D M] (peek @stack)
               ;i (first (first M))
               ;a (+ 1 (* 2 n))
               ;b (- w i)
               ;k (min a b)]
           ;(swap! stack pop)
           ;(doseq [[x next-q_D] (malea.ma-fsa/transitions q_D)]
             ;(let [characteristic-vector (characteristic-vector this x term k i)
                   ;next-M (transition M characteristic-vector)]
               ;(when-not (nil? next-M)
                 ;(let [next-V (str V x)]
                   ;(swap! stack conj [next-V next-q_D next-M])
                   ;(when (malea.ma-fsa/final? next-q_D)
                     ;(let [distance (minimum-distance this next-M w)]
                       ;(when (<= distance n)
                         ;(swap! matches conj [next-V distance]))))))))))
       ;@matches))
   
   ;clojure.lang.IFn

   ;(invoke [this term n]
     ;(transduce this term n)) 

   ;(invoke [this term]
     ;(this term 2)))

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
                    [(+ i 2) 1 0] ;; was [(+ i j 1) j 0], but (= j 1), so I simplified it
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
                        [(+ i 2) (+ e 1) 0] ;; was [(+ i j 1) (+ e j) 0], but (= j 1), so I simplified it
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
          (if (or (pos? (- e (nth p 1)))
                  (pos? (- i (nth p 0)))
                  (pos? (- x (nth p 2))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e,s j,f,t n]
    (if (= s 1)
      (if (= t 1)
        (= i j)
        (and (= f n) (= i j)))
      (if (= t 1)
        (<= (+ 1 (abs-int (- j i)))
            (- f e))
        (<= (abs-int (- j i))
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
              minimum-distance (if (and (= x 1) (< distance minimum-distance))
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
          (if (or (pos? (- e (nth p 1)))
                  (pos? (- i (nth p 0)))
                  (pos? (- x (nth p 2))))
            (recur (inc k) u)
            (recur l k))))))

  (subsumes? [this i,e,s j,f,t]
    (if (and (= s 1) (= t 0)) false
      (<= (abs-int (- j i))
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
              minimum-distance (if (and (= x 1) (< distance minimum-distance))
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
   (let [dfa-dictionary (malea.ma-fsa/ma-fsa dictionary sorted)]
     (transducer dfa-dictionary algorithm)))
  ([dictionary sorted]
   (transducer-from-list dictionary sorted STANDARD))
  ([dictionary]
   (transducer-from-list dictionary false)))
