(defproject image-resize-service "0.1.0-SNAPSHOT"
  :description "Image resize service"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-defaults "0.1.4"]
                 [ring/ring-mock "0.2.0"]
                 [compojure "1.3.2"]
                 [image-resizer "0.1.6"]]
  :plugins [[lein-ring "0.9.1"]]
  :ring {:port 4000
         :handler image-resize-service.routes/app})
