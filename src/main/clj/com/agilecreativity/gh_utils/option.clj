(ns com.agilecreativity.gh_utils.option
  (:require [clojure.string :as str]))

(def default-config "~/Dropbox/config/github.edn")

(def default-remote-label "origin")

(def options
  [["-c" "--config CONFIG" :default default-config]
   ["-r" "--repo REPO"]
   ["-i" "--init-commit"]
   ["-l" "--remote-label REMOTE_LABEL" :default default-remote-label]
   ["-p" "--push"]
   ["-h" "--help"]])

(defn usage [summary]
  (str/join \newline ["Create new Github's project from a command line"
                      ""
                      "Usage: gh-utils [options]"
                      summary
                      ""
                      "Options:"
                      ""
                      "--config       CONFIG       full path to the config file e.g. ~/Dropbox/config/github.edn"
                      "--init-commit               run git-init and git commit on the local project"
                      "--repo         REPO         name of repository to be created"
                      "--remote-label REMOTE_LABEL remote label default to 'origin'"
                      "--push                      push the code to the remote repository as well"
                      ""
                      "Examples:"
                      ""
                      "a) To push existing project that have already contain some commit (e.g. skip -i)"
                      "$gh-utils -c ~/Dropbox/config/github.edn -r awesome-idea -l upstream -p"
                      ""
                      "b) To create and push brand new project to Github and run initial commit"
                      "$gh-utils -c ~/Dropbox/config/github.edn -r awesome-idea -i -l origin -p"
                      ""
                      "c) To create brand new project to Github, run initial commit and skip pushing to remote branch."
                      "$gh-utils -c ~/Dropbox/config/github.edn -r awesome-idea -i -l origin"]))

(defn error-message [errors]
  (str "The following error occured while parsing your commands: \n\n"
       (str/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))
