# Demo project for fuzzing Clojure code

This is a small demo project showcasing how
[jazzer-clj](https://github.com/CodeIntelligenceTesting/jazzer-clj) can be used
to fuzz-test Clojure code.

## Usage

Build a JAR with `lein uberjar`. Then run the fuzzer on it as follows:

``` shell
docker run -v $PWD:/fuzzing cifuzz/jazzer                                       \
       --cp=/fuzzing/target/jazzer-clojure-0.1.0-SNAPSHOT-standalone.jar        \
       --target-class=jazzer_clojure_example.targets.SimpleExample
```

This will run [Jazzer](https://github.com/CodeIntelligenceTesting/jazzer) using
the official Docker image, telling it to fuzz the `SimpleExample` target defined
in [core.clj](src/jazzer_clojure_example/core.clj). Alternatively, you can run
Jazzer on the `JsonistaExample` to fuzz Metosin's excellent JSON library (which
we've chosen arbitrarily to demonstrate how to test libraries):

``` shell
docker run -v $PWD:/fuzzing cifuzz/jazzer                                       \
       --cp=/fuzzing/target/jazzer-clojure-0.1.0-SNAPSHOT-standalone.jar        \
       --target-class=jazzer_clojure_example.targets.JsonistaExample            \
       /fuzzing/corpus-jsonista
```

Note the additional argument `/fuzzing/corpus-jsonista` at the end. This tells
Jazzer to store all generated inputs that it considers useful into the
`corpus-jsonista` directory in this project. Storing a corpus this way is useful
if you want to be able to interrupt the fuzzer and resume it later without
having to redo a lot of work: it will more or less continue from the state where
you stopped it. (Check out the files in the corpus after running the fuzzer for
a while! Most of them will contain somewhat creative JSON data that the fuzzer
has come up with.)

See the [Jazzer homepage](https://github.com/CodeIntelligenceTesting/jazzer) for
more details about the fuzzer and how to configure it. Happy fuzzing!

## License

Copyright © 2022 Code Intelligence GmbH

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

<p align="center">
<a href="https://www.code-intelligence.com"><img src="https://www.code-intelligence.com/hubfs/Logos/CI%20Logos/CI_Header_GitHub_quer.jpeg" height=50px alt="Code Intelligence logo"></a>
</p>
