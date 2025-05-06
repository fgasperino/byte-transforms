(ns build
  (:refer-clojure :exclude [compile])
  (:require [clojure.tools.build.api :as b]))

(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))

(defn compile
  [_]
  (b/delete {:path "target"})
  (b/javac {:basis basis
            :src-dirs ["src" "src/byte_transforms"]
            :class-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src" "src/byte_transforms"]
                  :class-dir class-dir
                  :filter-nses '[byte-transforms]
                  :sort :topo
                  #_#_:ns-compile '[byte-transforms]}))
