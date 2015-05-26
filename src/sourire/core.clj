(ns sourire.core
  (:require [plumbing.core :refer [map-keys map-vals]]
            [sourire.victorinox :refer [kw->str]])
  (:import [com.ggasoftware.indigo Indigo
                                   IndigoRenderer]))

(defn clean [opts] (->> opts
                        (map-keys kw->str)
                        (map-vals #(or % ""))))

(defn add-defaults [opts] (-> opts
                              (assoc "ignore-stereochemistry-errors" true)
                              (assoc "ignore-noncritical-query-features" true)
                              (assoc "render-output-format" "png")))

(defn init-opts [opts] (-> opts clean add-defaults))

(defn init-indigo+renderer
  "Initialize an Indigo and IndigoRenderer instance. 
   Due to Indigo idiosyncrasies, the order of initialization has to be as follows:
   - initialize Indigo instance
   - create IndigoRenderer with the Indigo instance
   - lastly, set the optional parameters on the Indigo instance
   
   This behaviour is as designed: see following discussion thread:
   https://groups.google.com/forum/#!msg/indigo-general/2KQTqIudqDQ/xu3_hajYXF4J"
  ([] (init-indigo+renderer {}))
  ([opts]
   (let [indigo (new Indigo)
         renderer (IndigoRenderer. indigo)]
     (doseq [[k v] (init-opts opts)]
       (doto indigo (.setOption k v)))
     {:indigo   indigo
      :renderer renderer})))

(defn render-to-buffer
  "Accepts an Indigo instance and a smiles string.
   Returns the molecule image as a byte array buffer."
  [{:keys [indigo renderer]} smi]
  (let [molecule (.loadMolecule indigo smi)]
    (.renderToBuffer renderer molecule)))

(defn render-to-file
  "Accepts an Indigo instance, a smiles string and a file-name
   to which to render the molecule image as png."
  [{:keys [indigo renderer]} smi file-name]
  (let [molecule (.loadMolecule indigo smi)]
    (.renderToFile renderer molecule file-name)))