(defproject gh-utils "0.3.5"
  :description "Create new Github repository from the comfort of your command line; TL;DR; $gh-utils --config ~/Dropbox/config/github.edn --repo my-awesome-idea"
  :url "https://github.com/agilecreativity/gh-utils"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[lein-binplus "0.6.4"]]}
             :uberjar {:aot :all}}
  :source-paths ["src/main/clj"]
  :test-paths ["src/test/clj"]
  :bin {:name "gh-utils"
        :bin-path "~/bin"
        :bootclasspath false}
  :plugins [[lein-binplus "0.6.4"]
            [lein-cljfmt "0.5.7"]
            [lein-auto "0.1.3"]]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [irresponsible/tentacles "0.6.1"]
                 [me.raynes/fs "1.4.6"]
                 [clj-jgit "0.8.10"]
                 [aero "1.1.3"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :main com.agilecreativity.gh_utils.core)
