(ns sourire.core-test
  (:require [midje.sweet :refer :all]
            [sourire.core :refer :all]))

(facts "about initializing Indigo"
       
       (fact "without options"
             
             (init-indigo+renderer) => truthy)

       (fact "with options Map[Keyword, String]"
             
             (init-indigo+renderer {:render-comment "Glucose"}) => truthy)
       
       (fact "with options Map[String, String]"
             
             (init-indigo+renderer {"render-comment" "Glucose"}) => truthy))

(def nicotine "CN1CCC[C@H]1c2cccnc2")

(facts "about rendering to a buffer"
       
       (fact "without options"

             (-> (init-indigo+renderer)
                 (render-to-buffer nicotine)) => truthy)
       
       (fact "with options"

             (-> (init-indigo+renderer {:render-comment "Nicotine"})
                 (render-to-buffer nicotine)) => truthy))
