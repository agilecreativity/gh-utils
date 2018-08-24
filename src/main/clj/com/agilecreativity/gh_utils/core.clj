(ns com.agilecreativity.gh_utils.core
  (:gen-class)
  (:require [aero.core :refer [read-config]]
            [clojure.string :as str]
            [clojure.tools.cli :as cli]
            [com.agilecreativity.gh_utils.git_helper :as hlp]
            [com.agilecreativity.gh_utils.option :as opt]
            [me.raynes.fs :as fs]
            [tentacles.repos :as repos]))

(defn- expand-and-normalize
  "Allow the ~ to be expanded."
  [filename]
  (-> filename
      fs/expand-home
      fs/normalized))

(defn- default-options [options]
  "Define the sensible default options when creating new Github repository"
  (merge {:public false
          :has-issue false
          :has-wiki false
          :has-download false}
         options))

(defn- check-and-confirm-response
  "Check and confirm if we can successfully create a new Github repository."
  [response]
  (if (:status response)
    (println "Problem creating new repository, errors : " (get-in response [:body :errors]))
    (println (str "You have succesfully created new repository at : " (:html_url response)))))

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
                homepage (str github-prefix (str/join "/" (list username reponame)))
                response (repos/create-repo reponame (default-options {:auth auth
                                                                       :description (str reponame " by " username)
                                                                       :homepage homepage}))]
            (check-and-confirm-response response)

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
                (if push (hlp/git-push-remote base-dir remote-label ""))
                ;; No valid git project, so let the the user know
                (println "FYI: As you don't have the valid git project, you may like to run git init && git push manually."))))))
      (opt/exit 0 "Done")
      (catch Exception e
        ;; Handle any problem/exception that we may have
        (opt/exit 1 (println (str "Error loading configuration file: " (.getMessage e))))))))

(defn -main [& args]
  (let [{:keys [options arguments errors summary]}
        (cli/parse-opts args opt/options)]
    (cond
      (:help options)
      (opt/exit 0 (opt/usage summary))

      (:config options)
      (if (:repo options)
        (create-new-repo! options)
        (opt/exit 0 (opt/usage summary)))

      ;; Printout the usage then exit
      :else (opt/exit 0 (opt/usage summary)))))
