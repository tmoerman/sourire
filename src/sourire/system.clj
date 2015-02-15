(ns sourire.system
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :refer [run-server]]
            [taoensso.timbre :refer [info]]
            [sourire.handler :refer [api-handler]]))

(defrecord WebServer [port]
  component/Lifecycle

  (start [this]
    (info "[WebServer] starting...")
    (let [server-port     (or port 8080)
          server-shutdown (run-server api-handler {:port server-port})]
      (info "[WebServer] started on port:" server-port)
      (assoc this :server-shutdown server-shutdown)))

  (stop [this]
    (info "[WebServer] stopping...")
    (when-let [server-shutdown (:server-shutdown this)]
      (server-shutdown))
    (info "[WebServer] stopped.")
    (dissoc this :server-shutdown)))

(defn create-web-server [cfg] (map->WebServer cfg))

(defn new-system
  [cfg]
  (do
    (-> (component/system-map
          :web-server   (create-web-server (get-in cfg [:web-server])))
        (component/system-using
          {}))))