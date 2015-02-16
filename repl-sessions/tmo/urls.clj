
(def flavopereirin "CCc(c1)ccc2[n+]1ccc3c2Nc4c3cccc4")
(def nicotine      "CN1CCC[C@H]1c2cccnc2")
(def aflatoxin     "O1C=C[C@H]([C@H]1O2)c3c2cc(OC)c4c3OC(=O)C5=C4CCC(=O)5")
(def glucose       "OC[C@@H](O1)[C@@H](O)[C@H](O)[C@@H](O)[C@@H](O)1")
(def thiamine      "OCCc1c(C)[n+](=cs1)Cc2cnc(C)nc(N)2")

(defn gen-url
  [smi]
  (str "http://localhost:8000/molecule/" (url-encode smi)))

(gen-url flavopereirin)

(gen-url thiamine)
