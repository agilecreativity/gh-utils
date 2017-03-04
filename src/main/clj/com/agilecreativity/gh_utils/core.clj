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
        ;; TODO: may be add the options to push the commit to Github
        (println (str "You have succesfully created new repository at : " url)
                 (str "\nYou can track this repository with (https) : " https-url)
                 (str "\nYou can track this repository with (ssh)   : " ssh-url))))))

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

            ;; Now we are ready to add remote and push it upstream
            (let [base-dir (fs/file ".")]
             (hlp/git-init-and-add-remote username
                                          reponame
                                          base-dir
                                          "origin")
             (hlp/git-push-remote base-dir)))))

      ;; Handle any problem/exception that we may have
      (catch Exception e
        (exit 1 (println (str "Error loading configuration file: " (.getMessage e))))))))

(defn -main [& args]
  (println (str "Your dir:" (fs/file ".")))
  (let [{:keys [options arguments errors summary]}
         (cli/parse-opts args opt/options)]
     (cond
       (:help options)
       (exit 0 (usage summary))
       (:config options)
       (create-new-repo! options))))
