(ns image-resize-service.routes-test
  (:require [clojure.test :refer :all]
            [image-resize-service.routes :refer :all]
            [ring.mock.request :as mock :refer [request header]]))

(defn- test-status
  [status response]
  (is (= status (:status response))))

(deftest not-found-test
  (testing "Not found"
    (let [response (app (request :get "/"))]
      (is (= 404 (:status response))))))

(deftest image-resize-test
  (testing "Status 200"
    (let [response (app (request :get "/200/200/http%3A%2F%2Ffc04.deviantart.net%2Ffs16%2Ff%2F2007%2F127%2F2%2Fd%2FWallpaper_Request_by_Finvara.jpg"))]
      (test-status 200 response)))
  (testing "Invalid params"
    (let [response (app (request :get "/a/b/www.abc.com"))]
      (test-status 404 response)))
  (testing "Not image"
    (let [response (app (request :get "/200/200/https%3A%2F%2Fwww.google.ru"))]
      (test-status 422 response)
      (is (= "Cannot read image" (:body response))))))
