(defproject org.clj-commons/byte-transforms (or (System/getenv "PROJECT_VERSION") "0.3.0")
  :description "Methods for hashing, compressing, and encoding bytes."
  :license {:name "Apache License 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :deploy-repositories [["clojars" {:url "https://repo.clojars.org"
                                    :username :env/clojars_username
                                    :password :env/clojars_password
                                    :sign-releases true}]]
  :dependencies [[org.clj-commons/byte-streams "0.3.4"]
                 [org.xerial.snappy/snappy-java "1.1.10.7"]
                 [commons-codec/commons-codec "1.18.0"]
                 [org.lz4/lz4-java "1.8.0"]
                 [org.apache.commons/commons-compress "1.27.1"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.12.0"]
                                  [criterium "0.4.6"]
                                  [org.clojure/test.check "1.1.0"]
                                  [codox-md "0.2.0" :exclusions [org.clojure/clojure]]]}
             :ci {:javac-options ["-target" "1.8" "-source" "1.8"]
                  :dependencies [[org.clojure/clojure "1.10.1"]
                                 [criterium "0.4.6"]
                                 [org.clojure/test.check "1.1.0"]]}}
  :plugins [[codox "0.6.4"]]
  :codox {:writer codox-md.writer/write-docs
          :include [byte-transforms]}
  :java-source-paths ["src"]
  :global-vars {*warn-on-reflection* true}
  :jvm-opts ^:replace ["-server"]
  :test-selectors {:default (complement :benchmark)
                   :benchmark :benchmark})
