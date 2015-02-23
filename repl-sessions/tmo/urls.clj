
(def flavopereirin "CCc(c1)ccc2[n+]1ccc3c2Nc4c3cccc4")
(def nicotine      "CN1CCC[C@H]1c2cccnc2")
(def aflatoxin     "O1C=C[C@H]([C@H]1O2)c3c2cc(OC)c4c3OC(=O)C5=C4CCC(=O)5")
(def glucose       "OC[C@@H](O1)[C@@H](O)[C@H](O)[C@@H](O)[C@@H](O)1")
(def thiamine      "OCCc1c(C)[n+](=cs1)Cc2cnc(C)nc(N)2")
(def caffeine      "CN1C=NC2=C1C(=O)N(C)C(=O)N2C")
(def oenanthotoxin "CCC[C@@H](O)CC\\C=C\\C=C\\C#CC#C\\C=C\\CO")
(def three-ketocarbofuran "CNC(=O)Oc1cccc2C(=O)C(C)(C)Oc12")

(def big-one "C[C@@](C)(O1)C[C@@H](O)[C@@]1(O2)[C@@H](C)[C@@H]3CC=C4[C@]3(C2)C(=O)C[C@H]5[C@H]4CC[C@@H](C6)[C@]5(C)Cc(n7)c6nc(C[C@@]89(C))c7C[C@@H]8CC[C@@H]%10[C@@H]9C[C@@H](O)[C@@]%11(C)C%10=C[C@H](O%12)[C@]%11(O)[C@H](C)[C@]%12(O%13)[C@H](O)C[C@@]%13(C)CO")

(defn gen-url [smi] (str "http://localhost:8000/molecule/" (url-encode smi)))

(def test-mol "Clc1ccccc1CN2CCc3sccc3C2")

(url-encode test-mol)

(gen-url oenanthotoxin)
(gen-url flavopereirin)
(gen-url thiamine)
(gen-url glucose)
(gen-url big-one)

(gen-url test-mol)

