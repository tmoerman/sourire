(ns sourire.core
  (:import [com.ggasoftware.indigo Indigo
                                   IndigoRenderer]))

(defn init-indigo-renderer
  [& opts]
  (let [indigo (new Indigo)
        renderer (new IndigoRenderer indigo)]

    (if-let [kvs (some-> opts seq first)]
      (doseq [[k v] kvs]
        (do (println k v)
            (doto indigo (.setOption k v)))))

    {:indigo indigo
     :renderer renderer}))

(defn render-to-file
  [smi file-name & opts]
  (let [{:keys [indigo renderer]} (init-indigo-renderer opts)
        molecule                  (.loadMolecule indigo smi)]
    (.renderToFile renderer molecule file-name)))

(defn render-to-buffer
  [smi & opts]
  (let [{:keys [indigo renderer]} (init-indigo-renderer opts)
        molecule                  (.loadMolecule indigo smi)]
    (.renderToBuffer renderer molecule)))