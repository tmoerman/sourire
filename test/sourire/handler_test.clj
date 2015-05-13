(ns sourire.handler-test
  (:require [midje.sweet :refer :all]
            [sourire.handler :refer [molecule-regex]]
            [sourire.victorinox :refer [url-encode url-decode]]))

(def smiles-molecules 
  
  "Test collection of molecule smiles strings."
  
  ["C"
   "O"
   "[H+]"
   "C#N"
   "[Na+].[O-]c1ccccc1"
   "O[C@H]1CCCC[C@H]1O"
   "O=C(O)C@HC@@HC(=O)O"
   "CN1CCC[C@H]1c2cccnc2"
   "CC(=O)O.CCO>>CC(=O)OCC"
   "Clc1ccccc1CN2CCc3sccc3C2"
   "[Cu+2].[O-]S(=O)(=O)[O-]"
   "CN1C=NC2=C1C(=O)N(C)C(=O)N2C"
   "CCc(c1)ccc2[n+]1ccc3c2Nc4c3cccc4"
   "OCCc1c(C)[n+](=cs1)Cc2cnc(C)nc(N)2"
   "CC(=O)OCCC(/C)=C\\C[C@H](C(C)=C)CCC=C"
   "CCC[C@@H](O)CC\\C=C\\C=C\\C#CC#C\\C=C\\CO"
   "CN(C)CC/C=C\\1c2ccccc2COc3ccc(cc\\13)CC(=O)O"
   "C(=C/c1ccccc1)/CN2CCN(CC2)C(c3ccccc3)c4ccccc4"
   "OC[C@@H](O1)[C@@H](O)[C@H](O)[C@@H](O)[C@@](O)1"
   "O1C=C[C@H]([C@H]1O2)c3c2cc(OC)c4c3OC(=O)C5C4CCC(=O)5"
   "CC(=[O:1])[OH:2] . CC[OH:3] > [H+] > CC(=[O:2])[O:3]CC . [OH2:1]"
   "COC(=O)C(\\C)=C\\C1C(C)(C)[C@H]1C(=O)O[C@@H]2C(C)=C(C(=O)C2)CC=CC=C"
   "C[C@@](C)(O1)C[C@@H](O)[C@@]1(O2)[C@@H](C)[C@@H]3CC=C4[C@]3(C2)C(=O)C[C@H]5[C@H]4CC[C@@H](C6)[C@]5(C)Cc(n7)c6nc(C[C@@]89(C))c7C[C@@H]8CC[C@@H]%10[C@@H]9C[C@@H](O)[C@@]%11(C)C%10=C[C@H](O%12)[C@]%11(O)[C@H](C)[C@]%12(O%13)[C@H](O)C[C@@]%13(C)CO"])

(defn molecule-matches-regex
  "Returns whether the url encoded smiles string matches entirely 
   the molecule regex the handler uses for matching url patterns."
  [smiles]
  (let [encoded (url-encode smiles)]
    (->> encoded
         (re-find molecule-regex)
         url-decode
         (= smiles))))

(defn molecule-with-suffix-matches-regex
  [smiles]
  (let [encoded (url-encode smiles)
        with-suffix (str encoded "/image.png")]
    (->> with-suffix
         (re-find molecule-regex)
         url-decode
         (= smiles))))

(facts "about matching url encoded molecules"

       (fact "Smiles strings should match themselves"

             (->> smiles-molecules
                  (map molecule-matches-regex)) => (has every? true?))
       
       (fact "Smiles strings with /image.png suffix should only match encoded smiles string"

             (->> smiles-molecules
                  (map molecule-with-suffix-matches-regex)) => (has every? true?)))


(def encoded-formula-with-parens "O%3DC1OC(c2ccccc12)(c3ccc(O)cc3)c4ccc(O)cc4")

(fact "about accepting parens '()' in the encoded url"

      (re-find molecule-regex encoded-formula-with-parens) => encoded-formula-with-parens

       )