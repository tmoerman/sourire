(ns sourire.core
  (:require [plumbing.core :refer [map-keys]]
            [sourire.victorinox :refer [kw->str]])
  (:import [com.ggasoftware.indigo Indigo
                                   IndigoRenderer]))

(defn init-indigo
  "Initializes an Indigo instance.
   Accepts an optional map representing the options for the indigo instance.
   The map may consist of either [string, string] or [keyword, string] pairs."
  [& opts]
  (let [kvs (some->> opts seq first (map-keys kw->str))
        indigo (new Indigo)]
    (doseq [[k v] kvs] (doto indigo (.setOption k v)))
    indigo))

(defn render-to-file
  "Accepts an Indigo instance, a smiles string and a file-name
   to which to render the molecule image as png."
  [indigo smi file-name]
  (let [renderer (IndigoRenderer. indigo)
        molecule (.loadMolecule indigo smi)]
    (.renderToFile renderer molecule file-name)))

(defn render-to-buffer
  "Accepts an Indigo instance and a smiles string.
   Returns the molecule image as a byte array buffer."
  [indigo smi]
  (let [renderer (IndigoRenderer. indigo)
        molecule (.loadMolecule indigo smi)]
    (.setOption indigo "render-output-format" "png")
    (.renderToBuffer renderer molecule)))
