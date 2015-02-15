(ns user
  (:require [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.tools.namespace.repl :refer [refresh refresh-all set-refresh-dirs]]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.pprint :refer [pprint]]

            [com.stuartsierra.component :as component]

            [sourire.victorinox :refer :all :as vx]
            [sourire.system :refer :all :as sys]))

;.
;;= System lifecycle
;'

(def system nil)

(defn dev-system [] (-> "sourire-cfg.edn" vx/slurp-edn new-system))

(defn init [] (alter-var-root #'system (constantly (dev-system))))

(defn start [] (alter-var-root #'system component/start))

(defn stop [] (alter-var-root #'system (fn [s] (when s (component/stop s)))))

(defn launch [] (init) (start) :ready)

(defn reset [] (stop) (set-refresh-dirs "src" "dev") (refresh :after 'user/launch))