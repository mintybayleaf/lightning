(ns user
  (:require [clojure.tools.nrepl.server :as nrepl]))

(def nrepl-server (atom nil))

(defn nrepl
  []
  (reset! nrepl-server (nrepl/start-server))
  (spit "./.nrepl-port" (:port @nrepl-server)))