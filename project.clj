(defproject sourire "0.1.0-SNAPSHOT"

  :description "Sourire - a web API for the GGA Software Indigo library for converting
                Smiles molecule string representations to .png images."

  :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [com.stuartsierra/component "0.2.2"]
                 [com.taoensso/timbre "3.3.1"]
                 [prismatic/plumbing "0.3.7"]

                 [bidi "1.18.0"]
                 [ring "1.3.2"]
                 [http-kit "2.1.16"]

                 [com.ggasoftware/indigo "1.1.12"]
                 [com.ggasoftware.indigo/indigo-renderer "1.1.12"]]

  :source-paths ["src"]

  :profiles {:user {:source-paths ["dev"]
                    :repl-options {:port 8777}}

             :dev {:dependencies [[org.clojure/tools.namespace "0.2.9"]
                                  [org.clojure/tools.trace "0.7.8"]
                                  [org.clojure/java.classpath "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.7"]

                                  [midje "1.6.3"]
                                  [aprint "0.1.1"]]

                   :plugins [[lein-midje "3.1.3"]
                             [lein-kibit "0.0.8"]]

                   :main user}}

  :aliases {"user-repl" ["with-profile" "+user" "repl"]})