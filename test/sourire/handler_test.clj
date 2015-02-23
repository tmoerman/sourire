(ns sourire.handler-test
  (:require [midje.sweet :refer :all]
            [sourire.handler :refer [molecule-regex]]
            [sourire.victorinox :refer [url-encode]]))

(def smiles-molecules 
  
  "A test collection of molecule smiles strings."
  
  ["C"
   "O"
   "[H+]"
   "C#N"
   "CC(=O)O.CCO>>CC(=O)OCC"
   "CCc(c1)ccc2[n+]1ccc3c2Nc4c3cccc4"
   "CN1CCC[C@H]1c2cccnc2"
   "O1C=C[C@H]([C@H]1O2)c3c2cc(OC)c4c3OC(=O)C5C4CCC(=O)5"  
   "OC[C@@H](O1)[C@@H](O)[C@H](O)[C@@H](O)[C@@](O)1"
   "OCCc1c(C)[n+](=cs1)Cc2cnc(C)nc(N)2"
   "CN1C=NC2=C1C(=O)N(C)C(=O)N2C"
   "CCC[C@@H](O)CC\\C=C\\C=C\\C#CC#C\\C=C\\CO"
   "Clc1ccccc1CN2CCc3sccc3C2"
   "CC(=[O:1])[OH:2] . CC[OH:3] > [H+] > CC(=[O:2])[O:3]CC . [OH2:1]"
   "C[C@@](C)(O1)C[C@@H](O)[C@@]1(O2)[C@@H](C)[C@@H]3CC=C4[C@]3(C2)C(=O)C[C@H]5[C@H]4CC[C@@H](C6)[C@]5(C)Cc(n7)c6nc(C[C@@]89(C))c7C[C@@H]8CC[C@@H]%10[C@@H]9C[C@@H](O)[C@@]%11(C)C%10=C[C@H](O%12)[C@]%11(O)[C@H](C)[C@]%12(O%13)[C@H](O)C[C@@]%13(C)CO"
   "[Na+].[O-]c1ccccc1"
   "O[C@H]1CCCC[C@H]1O"
   "O=C(O)C@HC@@HC(=O)O"])

(defn molecule-matches-regex
  "Returns whether the url encoded smiles string matches entirely 
   the molecule regex the handler uses for matching url patterns."
  [smiles]
  (let [encoded (url-encode smiles)]
    (= encoded 
       (re-find molecule-regex encoded))))

(defn molecule-with-suffix-matches-regex
  [smiles]
  (let [encoded (url-encode smiles)
        with-suffix (str encoded "/image.png")]
    (= encoded
       (re-find molecule-regex with-suffix))))

(facts "about matching url encoded molecules"

       (fact "Smiles strings should match themselves"

             (for [m smiles-molecules]
               (molecule-matches-regex m)) => (has every? #(= % true)))
       
       (fact "Smiles strings with /image.png suffix should only match encoded smiles string"

             (for [m smiles-molecules]
               (molecule-with-suffix-matches-regex m)) => (has every? #(= % true))))