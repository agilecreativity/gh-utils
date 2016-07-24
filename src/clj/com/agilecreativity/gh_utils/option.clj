(ns com.agilecreativity.gh_utils.option
  (:require [clojure.string :as string])
  (:gen-class))

(def default-config "~/Dropbox/github.edn")

(def options
  [["-c" "--config CONFIG" :default default-config]
   ["-r" "--repo REPO"]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["Create new Github's project from a command line"
        ""
        "Usage: gh-utils [options]"
        options-summary
        "Options:"
        ""
        "--config CONFIG full path to the config file e.g. ~/Dropbox/github.edn"
        "--repo   REPO   name of repository to be created"
        ""]
       (string/join \newline)))

(defn error-message [errors]
  (str "The following error occured while parsing your commands: \n\n"
       (string/join \newline errors)))

(defn exit [status msg]
  (println msg)
  (System/exit status))
