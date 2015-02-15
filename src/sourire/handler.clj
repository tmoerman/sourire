(ns sourire.handler
  (:require [bidi.bidi :as bidi]
            [bidi.ring :refer [make-handler]]
            [taoensso.timbre :refer [info]]
            [sourire.victorinox :refer [url-encoded]])
  (:use [ring.middleware params
                         keyword-params
                         nested-params]))

(defn serve-index [req] {:status 200 :body "Hello World!"})

(defn serve-molecule-image [req]
  (info "serve molecule image input" req)
  {:status 200 :body "O=O-C"})

(def all-routes ["/" {"index.html" serve-index
                      ["molecule/" [url-encoded :smi]] serve-molecule-image}])

(def api-handler (-> all-routes
                     make-handler
                     wrap-keyword-params
                     wrap-nested-params
                     wrap-params))