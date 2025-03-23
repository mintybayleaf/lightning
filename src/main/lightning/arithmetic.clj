(ns lightning.arithmetic)

;; unboxed longs

(defn addi64 ^long
  [^long x ^long y]
  (unchecked-add x y))

(defn subi64 ^long
  [^long x ^long y]
  (unchecked-subtract x y))

(defn multi64; ^long
  [^long x ^long y]
  (unchecked-multiply x y))

(defn divi64 ^long
  [^long x ^long y]
  (/ x y))

(defn inci64 ^long
  [^long x]
  (unchecked-inc x))

(defn deci64 ^long
  [^long x]
  (unchecked-dec x))

;; unboxed doubles

(defn addf64 ^double
  [^double x ^double y]
  (unchecked-add x y))

(defn subf64 ^double
  [^double x ^double y]
  (unchecked-subtract x y))

(defn multf64 ^double
  [^double x ^double y]
  (unchecked-multiply x y))

(defn divf64 ^double
  [^double x ^double y]
  (/ x y))

(defn incf64 ^double
  [^double x]
  (unchecked-inc x))

(defn decf64 ^double
  [^double x]
  (unchecked-dec x))

