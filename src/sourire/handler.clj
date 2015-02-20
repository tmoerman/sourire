(ns sourire.handler
  (:require [bidi.bidi :as bidi]
            [bidi.ring :refer [make-handler]]
            [taoensso.timbre :refer [info error]]
            [sourire.core :refer [init-indigo+renderer render-to-buffer]]
            [sourire.victorinox :refer [url-encoded url-decode]])
  (:use [ring.middleware params
         keyword-params
         nested-params])
  (:import (java.io ByteArrayInputStream)))

(defn serve-index [_]
  {:status 200 
   :body "Usage: \n\n [base-url]/molecule/[url-encoded-smiles-string]?indigo-param-name=param-value"})

(defn serve-molecule-image [req]
  (info "serving request" req)
  (try
    (let [params (req :params)
          smi    (-> (params :smi) url-decode)
          opts   (-> params (dissoc :smi))
          i+r    (init-indigo+renderer opts)
          
          image (->> smi
                     (render-to-buffer i+r)
                     (ByteArrayInputStream.))]
      {:status  200
       :body    image
       :headers {"Content-Type" "image/png"}})
    (catch Exception e
      (error e)
      {:status 400
       :body   (str (.getMessage e))})))

(def all-routes ["/" {"index.html"                     serve-index
                      ["molecule/" [url-encoded :smi]] serve-molecule-image}])

(def api-handler (-> all-routes
                     make-handler
                     wrap-keyword-params
                     wrap-nested-params
                     wrap-params))