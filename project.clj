(defproject jazzer-clojure-example "0.1.0-SNAPSHOT"
  :description "Example project for Jazzer with Clojure"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.code-intelligence/jazzer-clj "0.1.0"]
                 [metosin/jsonista "0.3.5"]]
  :repl-options {:init-ns jazzer-clojure.core}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
