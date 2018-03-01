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
            [tentacles.users :as t-users]
            [aero.core :refer [read-config]])
  (:gen-class) ;; TODO: may be we don't need this?
  )

(defn- expand-and-normalize
  "Allow the ~ to be expanded."
  [filename]
  (-> filename
      fs/expand-home
      fs/normalized))

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
        (println (str "You have succesfully created new repository at : " url))))))

(defn create-new-repo!
  "Create new repository using the given options"
  [options]
  (let [config-options (:config options)
        reponame (:repo options)]
    (try
      (if-let [config (read-config (expand-and-normalize config-options))]
        (let [username (:username config)
              password (:password config)
              auth (str username ":" password)]
          (let [github-prefix "https://github.com/"
                homepage (str github-prefix (clojure.string/join "/" (list username reponame)))
                result (t-repos/create-repo reponame (default-options {:auth auth
                                                                       :description (str reponame " by " username)
                                                                       :homepage homepage}))]
            (check-and-confirm-result result)

            ;; Make sure that we are running from the right directory
            (let [base-dir (fs/file ".")
                  {:keys [init-commit
                          remote-label
                          push]} options]

              ;; Don't run git-init commit if project already containing .git directory
              (if-not (fs/exists? ".git")
                (if init-commit (hlp/git-init-commit base-dir)))

              ;; We always add remote tracking
              (hlp/git-add-remote username reponame base-dir remote-label)

              ;; Run git push only if the user specify '--push' option.
              (if (fs/exists? ".git")
                (if push (hlp/git-push-remote base-dir remote-label "master"))
                (println "FYI: As you don't have the valid git project, you may like to run git init && git push manually."))))))

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
      (if (:repo options)
        (create-new-repo! options)
        (exit 0 (usage summary)))

      :else (exit 0 (usage summary)))))
