(defproject gh-utils "0.1.2"
  :description "Create new Github repository from your the comfort of your command line; TL;DR; $gh-utils --config ~/Dropbox/github.edn --repo my-awesome-idea"
  :url "https://github.com/agilecreativity/gh-utils"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :profiles {:dev {:dependencies [[lein-bin "0.3.4"]]}
             :uberjar {:aot :all}}
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :bin {:name "gh-utils"
        :bin-path "~/bin"
        :bootclasspath true}
  :plugins [[lein-bin "0.3.4"]
            [lein-cljfmt "0.5.3"]]
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]
                 [org.clojure/tools.cli "0.3.5"]
                 ;; NOTE: this is a workaround until
                 ;; as [the origin pr](https://github.com/Raynes/tentacles/pull/91) is
                 ;; still not accepted/merged by original author!
                 [org.clojars.agilecreativity/tentacles "0.5.2"]
                 [me.raynes/fs "1.4.6"]]
  :main com.agilecreativity.gh_utils.main)
