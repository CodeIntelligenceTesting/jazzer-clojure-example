(ns jazzer-clojure-example.core
  (:require [com.code-intelligence.jazzer-clj.core :as fuzzing]
            ;; This is just an example of code that we might want to test.
            [jsonista.core :as json]))

;;; First, the example from the jazzer-clj README.

(defn do-something
  "Not a very useful piece of code."
  [s]
  (when (= "supersecret" s)
    (throw (Exception. "You found the bug!"))))

(fuzzing/deftarget jazzer_clojure_example.targets.SimpleExample [input]
  ;; We ask the fuzzer for a string because that's the input needed for
  ;; `do-something`.
  (do-something (.consumeRemainingAsString input)))

;;; Alternatively, let's test some library code!

(fuzzing/deftarget jazzer_clojure_example.targets.JsonistaExample [input]
  ;; json/read-value expects a string, so we ask the fuzzer to give us string
  ;; data, and then we just feed that data into the library function.
  (try (json/read-value (.consumeRemainingAsString input))
       ;; IOException is the expected reaction when the input is malformed. It's
       ;; part of the library's interface, so we catch and ignore the exception
       ;; to prevent the fuzzer from reporting it as a bug.
       (catch java.io.IOException _ nil)))
