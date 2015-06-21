(ns image-resize-service.image-utils
  (:require [clojure.java.io :as io]
            [image-resizer.core :refer :all]
            [image-resizer.format :as format])
  (:import [java.net URL]
           [javax.imageio ImageIO]))

(defn- get-image
  [url]
  (ImageIO/read (URL. url)))

(defn resize-image
  [width height path]
  (try
    (-> path
        (get-image)
        (resize width height)
        (format/as-stream "jpg"))
    (catch IllegalArgumentException e
      (throw (IllegalArgumentException. "Cannot read image" e)))))
