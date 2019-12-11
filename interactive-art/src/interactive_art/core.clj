(ns skulls.core
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(def field-size 700)

(defn load-image [image]
  (let [path (str "images/" (name image) ".png")]
    (q/load-image path)))

(defn draw-image [state k [x y]]
  (q/image (get state [:images k]) x y))

(defn setup []
  (q/frame-rate 30)
  (q/color-mode :rgb)
  (q/image-mode :center)
  {:head (load-image "smiley1")
   :jaw (load-image "smiley2")})

(defn update-state [state]
  state)

(defn skull [x y state]
  ;(q/rotate (pulse -0.02 0.02 0.5))
  ;(q/translate (pulse 1 50 0.25) 0)
  (q/image (get state :head) x y)
  ;(q/translate 0 (pulse 1 100 0.25))
  (q/image (get state :jaw) x y))

(defn wild-skull [x y state scale]
  (q/with-translation [100 0 0]
    (q/scale scale)
    (skull x y state)))

(defn draw-state [state]
  (q/clear)
  (cond
    (q/key-pressed?) (skull (/ (q/width) 2) (/ (q/height) 2) state)
    )
)

(q/defsketch skulls
  :host "skulls"
  :size [1000 1000]
  :setup setup
  :update update-state
  :draw draw-state
  :middleware [m/fun-mode])

