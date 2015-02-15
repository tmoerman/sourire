
(use 'bidi.bidi)

(def smi-1 "C[C@@H]1C[C@H](C)C[C@@H](C)C1")

(defn url-encode [s] (java.net.URLEncoder/encode s))

(def url-encoded
  "A regex matching on url encoded strings."
  #".*%[0-9a-fA-F]+|\.\.")

(def all-routes ["/" {"index.html" :index
                      ["molecule/" [url-encoded :smi]] :molecule}])

(str "/molecule/" (url-encode smi-1))

(match-route all-routes (str "/molecule/" (url-encode smi-1)))
