(ns sourire.handler
  (:require [bidi.bidi :as bidi]
            [bidi.ring :refer [make-handler]]
            [taoensso.timbre :refer [info]]
            [sourire.core :refer [init-indigo render-to-buffer]]
            [sourire.victorinox :refer [url-encoded url-decode]])
  (:use [ring.middleware params
         keyword-params
         nested-params])
  (:import (java.io ByteArrayInputStream)))

(defn serve-index [req] {:status 200 :body "Hello World!"})

(defn serve-molecule-image [req]
  (info "serve molecule image input" req)

  (try
    (let [params (req :params)
          smi    (-> (params :smi)
                     (url-decode))
          opts   (-> params
                     (dissoc :smi)
                     (assoc :render-output-format "png"))
          indigo (init-indigo opts)
          bytes  (render-to-buffer indigo smi)
          image  (ByteArrayInputStream. bytes)]
      {:status  200
       :body    image
       :headers {"Content-Type" "image/png"}})
    (catch Exception e
      (.printStackTrace e)

      {:status 400
       :body   (str (.getMessage e))})))

(def all-routes ["/" {"index.html" serve-index
                      ["molecule/" [url-encoded :smi]] serve-molecule-image}])

(def api-handler (-> all-routes
                     make-handler
                     wrap-keyword-params
                     wrap-nested-params
                     wrap-params))