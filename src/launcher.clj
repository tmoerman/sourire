(ns launcher
  (:require [sourire.victorinox :refer [slurp-edn]]
            [sourire.system :refer [new-system]]
            [com.stuartsierra.component :refer [start stop]]
            [taoensso.timbre :refer [info]])
  (:gen-class))


(defn extract-port 
  [args]
  (some->> args
           (partition-all 2 1)
           (filter (fn [[k _]] (= k ":port")))
           first
           second
           Integer/parseInt))

(defn -main
  [& args]
  (let [port-from-args (extract-port args)
        
        default-config (slurp-edn "sourire-cfg.edn")
        
        config (if port-from-args
                 (assoc-in default-config [:web-server :port] port-from-args)
                 default-config)
        
        system (new-system config)]
    
    (info "Launching standalone Sourire system with config:" config)
    (start system)
    (.addShutdownHook (Runtime/getRuntime) (Thread. #(stop system)))))
