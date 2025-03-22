(ns build
  (:require [clojure.tools.build.api :as build]
            [deps-deploy.deps-deploy :as dd]))

(def lib 'bayleaf/lightning)
(def main 'lightning/core)
(def version (format "0.1.%s" (build/git-count-revs nil)))
(def build-directory "target")
(def class-directory (str build-directory "/classes"))
(def jar-file (format "%s/jar/%s-%s.jar" build-directory (name lib) version))
(def uberjar-file (format "%s/jar/%s-%s.jar" build-directory (name lib) version))

(def basis (delay (build/create-basis {:project "deps.edn"})))

(defn clean [_] (build/delete {:path build-directory}))

(defn uberjar [_]
  (build/copy-dir {:src-dirs ["src/main" "resources/main"] :target-dir class-directory})
  (build/compile-clj {:basis @basis :ns-compile [main] :class-dir class-directory})
  (build/uber {:class-dir class-directory
               :uber-file uberjar-file
               :main main
               :basis @basis
               :exclude [#"^META-INF/license/LICENSE.*"]}))

(defn jar [_]
  (build/write-pom {:class-dir class-directory
                    :lib lib
                    :version version
                    :basis @basis
                    :src-dirs ["src"]})
  (build/copy-dir {:src-dirs ["src/main" "resources/main"] :target-dir class-directory})
  (build/jar {:class-dir class-directory :jar-file jar-file}))

(defn install [_]
  (build/install {:basis @basis
                  :lib lib
                  :version version
                  :jar-file jar-file
                  :class-dir class-directory}))


(defn deploy [_]
  (dd/deploy {:installer :remote
              :artifact jar-file
              :pom-file (build/pom-path {:lib lib :class-dir lib})}))