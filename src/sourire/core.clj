(ns sourire.core
  (:import [com.ggasoftware.indigo Indigo
                                   IndigoRenderer]))

(defn init-indigo-context
  "Initializes an Indigo instance.
   Accepts an optional Map[String, String], representing the options for the indigo instance."
  [& opts]
  (let [indigo (new Indigo)]
    (doseq [[k v] (some-> opts seq first)]
      (doto indigo (.setOption k v)))
    indigo))

(defn render-to-file
  "Renders the specified smiles string smi to a png file with specified file name.
   Optionally accepts an options map."
  [smi file-name & opts]
  (let [indigo   (init-indigo-context opts)
        renderer (new IndigoRenderer indigo)
        molecule (.loadMolecule indigo smi)]
    (.renderToFile renderer molecule file-name)))

(defn render-to-buffer
  "Renders the specified smiles string smi to a png byte array buffer.
   Optionally accepts an options map."
  [smi & opts]
  (let [indigo   (init-indigo-context opts)
        renderer (new IndigoRenderer indigo)
        molecule (.loadMolecule indigo smi)]
    (.renderToBuffer renderer molecule)))