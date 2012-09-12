(ns examples.namespacing.myNS
  
  ;  java.lang is added implicitly 
  
  ; clojure.core is added implicitly. unless, we use refer-clojure to manage it
  ; note that all the basic operations are defined in clojure.core. This is important to
  ; remember when using the :only directive. In here the users of this namespace are not
  ; able to do division, as only +, - and * are used.
  (:refer-clojure :only [+ - *] ) 
  
  ; absorbing other namespaces into our namespace
  (:use 
    ; we need to define the path to the namespace
    ; it is possible to define the header portion of the namespace,
    ; and then add several endings
    (examples.namespacing  
          [a-to-d   :only     [b c] ]  ; this is the way to go
          [d-to-g  :exclude [d g] ] ; not a smart move - less control on the symbols you are adding
     )    ;  :verbose
   ) 
  
  ; only loading namespaces, the symbols within these namespaces are not absorbed into
  ; the current namespace. 
  ; We can we can always alias the loaded namespaces, to save some typing, using the :as directive
  ; If we need several namespaces with identical beginning, we write that beginning once,
  ; and add the different endings in a space separated manner
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
; uncomment the next line to get an error, as no 'a' symbol was defined in this namespace, or in the 
; part of the namespaces that we :used
; a;

;we get to the 'a' symbol by qualifing it with its namesapce
examples.namespacing.a-to-g/a ;= 100
; or with the alias of the namespace
A2G/a ; = 100

; what the :use directive is:
; load the entire namespace into memory, and add to the maps of the current namespace
; symbol based on the only/exclude directive
; we can read the value of 'a' from the a-to-d namespace, even though we asked only for
; b and c. BUT, we can do so only when qualifing the symbol with the namespace
examples.namespacing.a-to-d/a

; we can get to b and c without any namespace qualification, as we used the :use directive
b ;=2
c ;=3
; uncomment the next line to get an error , as the / (division) is not imported from clojure.core
;(/ 5 3)