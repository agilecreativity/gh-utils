(defproject gh-utils "0.2.0"
  :description "Create new Github repository from the comfort of your command line; TL;DR; $gh-utils --config ~/Dropbox/github.edn --repo my-awesome-idea"
  :url "https://github.com/agilecreativity/gh-utils"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[lein-bin "0.3.5"]]}
             :uberjar { :aot :all }}
  :source-paths ["src/main/clj"]
  :test-paths ["src/test/clj"]
  :bin {:name "gh-utils"
        :bin-path "~/bin"
        :bootclasspath true}
  :plugins [[lein-bin "0.3.5"]
            [lein-cljfmt "0.5.6"]
            [lein-auto "0.1.3"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [com.github.raynes/tentacles "master"]
                 [me.raynes/fs "1.4.6"]
                 [clj-jgit "0.8.9"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :main com.agilecreativity.gh_utils.core)
