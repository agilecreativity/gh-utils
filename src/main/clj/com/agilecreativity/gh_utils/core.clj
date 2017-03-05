(ns com.agilecreativity.gh_utils.core
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts] :as cli]
            [com.agilecreativity.gh_utils.option :refer :all :as opt]
            [com.agilecreativity.gh_utils.git_helper :refer :all :as hlp]
            [me.raynes.fs :as fs]
            [tentacles.data  :as t-data]
            [tentacles.repos :as t-repos]
            [tentacles.users :as t-users])
  (:gen-class))

(defn- load-config
  "Load config file that contains username/password information"
  [filename]
  (edn/read-string (slurp (-> filename
                              fs/expand-home
                              fs/normalized))))

(defn- default-options [& options]
  "Define the sensible default options when creating new Github repository"
  (let [opts (merge {:public false
                     :has-issue false
                     :has-wiki false
                     :has-download false}
                    (first options))]
    opts))

(defn- check-and-confirm-result
  "Check and confirm if we can successfully create a new Github repository."
  [result]
  (if (:status result)
    (println "Problem creating new repository, errors : " (get-in result [:body :errors]))
    (do
      (let [url (:html_url result)
            origin-prefix "git remote add origin "
            https-url (str origin-prefix url ".git")
            ssh-url (-> https-url
                        ;; Convert https:// to git@
                        (clojure.string/replace-first #"https://github.com/" "git@github.com:"))]
        (println (str "You have succesfully created new repository at : " url)
                 (str "\nIt is setup to track remote branch at          : " ssh-url))))))

(defn create-new-repo!
  "Create new repository using the given options"
  [options]
  (let [config-options (:config options)
        repo-options (:repo options)]
    (try
      (if-let [config (load-config config-options)]
        ;; Now check for repo name at this point
        (let [reponame (if repo-options
                         repo-options
                         (fs/base-name (fs/parent config-options)))
              username (:username config)
              password (:password config)
              auth (str username ":" password)]
          (let [github-prefix "https://github.com/"
                homepage (str github-prefix (clojure.string/join "/" (list username reponame)))
                result (t-repos/create-repo reponame
                                            (default-options {:auth auth
                                                              :description (str reponame " by " username)
                                                              :homepage homepage}))]
            (check-and-confirm-result result)

            (let [base-dir (fs/file ".")]
              ;; Add tracking to remote repository
              (hlp/git-init-and-add-remote username
                                           reponame
                                           base-dir
                                           "origin")
             ;; Push the change to remote if the user pass in the option --push or -p
              (if (:push options)
                (hlp/git-push-remote base-dir)
                (println "FYI: commit exist locally, but no git push is perform. You may run git push manually to publish your changes."))))))

      ;; Handle any problem/exception that we may have
      (catch Exception e
        (exit 1 (println (str "Error loading configuration file: " (.getMessage e))))))))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]}
         (cli/parse-opts args opt/options)]
     (cond
       (:help options)
       (exit 0 (usage summary))
       (:config options)
       (create-new-repo! options))))
