(ns image-resize-service.routes
  (:require [clojure.java.io :as io]
            [compojure.core :as compojure]
            [compojure.route :as route]
            [image-resize-service.image-utils :refer [resize-image]]
            [image-resize-service.utils :refer [parse-int]])
  (:use [compojure.core]
        [compojure.handler]))

(defn- response
  [message & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "text/plain;charset=utf-8"}
   :body message})

(defn- handle-resize-image
  [width height path]
  (try
    (resize-image (parse-int width) (parse-int height) path)
    (catch IllegalArgumentException e (response (.getMessage e) 422))))

(def test-page (slurp (io/resource "public/index.html")))

(defroutes compojure-handler
  (GET "/" [] test-page)
  (GET "/:width{[0-9]+}/:height{[0-9]+}/:path" [width height path] (handle-resize-image width height path))
  (route/not-found "<h3>page not found</h3>"))

(def app
  (-> compojure-handler
      (site)))
