(ns build
  (:require
   [clojure.tools.build.api :as build]))

(defn fuzzing-jar [_]
  (build/delete {:path "target"})
  (let [basis (build/create-basis {:project "deps.edn"
                                   :aliases [:jazzer]})]
    (build/compile-clj {:basis basis
                        :src-dirs ["src"]
                        :class-dir "target/classes"})
    (build/uber {:basis basis
                 :class-dir "target/classes"
                 :uber-file "target/fuzzing.jar"})
  (println "Fuzzing jar created: target/fuzzing.jar")))
