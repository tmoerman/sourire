
(use 'bidi.bidi)

(def smi-1 "C[C@@H]1C[C@H](C)C[C@@H](C)C1")
(def smi-2 "CCc(c1)ccc2[n+]1ccc3c2Nc4c3cccc4")
(def smi-3 "CN1CCC[C@H]1c2cccnc2")

(def all-routes ["/" {"index.html" :index
                      ["molecule/" [url-encoded :smi]] :molecule}])

(str "/molecule/" (url-encode smi-1))

(match-route all-routes (str "/molecule/" (url-encode smi-1)))
(match-route all-routes (str "/molecule/" (url-encode smi-2)))


(url-encode smi-1)

(url-encode smi-2)


(defn gen-url
  [smi]
  (str "http://localhost:8000/molecule/" (url-encode smi)))
