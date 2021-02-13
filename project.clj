(defproject gh-utils "0.4.0"
  :description "Create new Github repository from the comfort of your command line; TL;DR; $gh-utils --config ~/Dropbox/config/github.edn --repo my-awesome-idea"
  :url "https://github.com/agilecreativity/gh-utils"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[lein-binplus "0.6.6"]]}
             :uberjar {:aot :all}}
  :source-paths ["src/main/clj"]
  :test-paths ["src/test/clj"]
  :bin {:name "gh-utils"
        :bin-path "~/bin"
        :bootclasspath false}
  :plugins [[lein-binplus "0.6.6"]
            [lein-ancient "0.6.15"]
            [lein-cljfmt "0.7.0"]
            [lein-auto "0.1.3"]]
  :dependencies [[org.clojure/clojure "1.10.2" :scope "provided"]
                 [org.clojure/tools.cli "1.0.194"]
                 [irresponsible/tentacles "0.6.6"]
                 [clj-commons/fs "1.6.307"]
                 [clj-jgit "1.0.1"]
                 [aero "1.1.6"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :deploy-repositories [["clojars"  {:sign-releases false :url "https://clojars.org/repo"}]
                        ["snapshots" {:sign-releases false :url "https://clojars.org/repo"}]]
  :main com.agilecreativity.gh_utils.core)
