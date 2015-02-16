(ns sourire.victorinox
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import [java.net URLEncoder
                     URLDecoder]))

(defn kw->str [x] (if (keyword? x) (name x) x))

(defn slurp-edn
  "Slurp the specified .edn file."
  [file]
  (-> file io/resource slurp edn/read-string))

(defn url-encode
  "URL encode the specified string s."
  [s]
  (URLEncoder/encode s "UTF-8"))

(defn url-decode
  [s]
  (URLDecoder/decode s))

(def url-encoded
  "A regex matching on url encoded strings."
  #".*%[0-9a-zA-Z]+|\.\.")