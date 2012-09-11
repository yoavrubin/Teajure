(ns examples.namespacing.myNS
  
  ;  java.lang is added implicitly 
  
  ; clojure.core is added implicitly. unless, we use refer-clojure to manage it
  (:refer-clojure :only [+ - *] ) 
  
  ; absorbing other namespaces into our namespace
  (:use 
    (examples.namespacing 
          [a-to-d   :only     [b c] ]  ; this is the way to go
          [d-to-g  :exclude [d g] ] ; not a smart move
     )    ;  :verbose
   ) 
  
  ; defining dependencies to other namespaces
  ; we can  also alias the dependencies
  (:require 
    (examples.namespacing 
         [a-to-g :as A2G ] 
         c-to-f
    ) ; :verbose
    (clojure 
      walk 
      [zip :as CZ]
    ) 
   )
  ;defining dependencies in the hosting environment (Java)
  (:import 
    (java.util 
      logging.Logger 
      jar.JarFile
      ArrayList
    )
  )
)

