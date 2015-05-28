(ns sourire.handler
  (:require [bidi.ring :refer [make-handler]]
            [taoensso.timbre :refer [info error]]
            [sourire.core :refer [init-indigo+renderer render-to-buffer]]
            [sourire.victorinox :refer [url-decode]])
  (:use [ring.middleware params
         keyword-params
         nested-params])
  (:import (java.io ByteArrayInputStream))
  (:import com.ggasoftware.indigo.IndigoException))

(defn serve-index [_]
  {:status 200 
   :body "Usage: \n\n [base-url]/molecule/[url-encoded-smiles-string]?indigo-param-name=param-value"})

(defn png-response [i+r smiles]
  (let [image (->> smiles
                   (render-to-buffer i+r)
                   (ByteArrayInputStream.))]
    {:status  200
     :body    image
     :headers {"Content-Type" "image/png"}}))

(defn serve-molecule-image [req]
  (info "serving request" req)
  (try
    (let [params (req :params)
          smi    (-> (params :smi) url-decode)
          opts   (-> params (dissoc :smi))
          i+r    (init-indigo+renderer opts)]
      (try
        (png-response i+r smi)
        (catch IndigoException e
          (error e)
          (png-response i+r "[Ba][C][K][U][P]"))))
    (catch Exception e
      (error e)
      {:status 400
       :body   (str (.getMessage e))})))

(def molecule-regex #"[a-zA-Z0-9%\.\+\-\_\*\(\)]+")

(def all-routes ["/" {""           serve-index
                      "index.html" serve-index
                      ["molecule/" [molecule-regex :smi]] serve-molecule-image
                      ["molecule/" [molecule-regex :smi] "/image.png"] serve-molecule-image}])

(def api-handler (-> all-routes
                     make-handler
                     wrap-keyword-params
                     wrap-nested-params
                     wrap-params))
