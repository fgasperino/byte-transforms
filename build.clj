(ns build
  (:refer-clojure :exclude [compile])
  (:require [clojure.tools.build.api :as b]))

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))

(defn compile
  [_]
  (b/delete {:path "target"})
  (b/javac {:src-dirs ["src/byte_transforms"]})
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir
                  :filter-nses '[byte-transforms]
                  :ns-compile '[byte-transforms]}))
