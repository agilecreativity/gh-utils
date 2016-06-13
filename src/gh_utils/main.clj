(ns gh-utils.main
  (:require [tentacles.repos :as t-repos]
            [tentacles.users :as t-users])
  (:gen-class))

(defn -main [& args]
  (println "About to create new repos.."))
  (let [auth "agilecreativity:<YOUR-PASSWORD>"]
    (print (str "Your credential:" auth))
    (print (t-users/me {:auth auth})))
