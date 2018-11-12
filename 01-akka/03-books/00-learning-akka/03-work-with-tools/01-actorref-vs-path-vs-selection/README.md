# ActorRef vs Actor Path vs Actor Selection

https://www.safaribooksonline.com/library/view/learning-akka/9781784391836/video3_1.html

- ActorRef

Actor Reference is a reference to an Actor
Actor Reference does not change during Actor lifecycle
Actor Reference will point to a Dead-Letter when the Actor is shutdown

- Actor Path

Actor Path is location of the Actor on Actor system tree
i.e. My Path: /user/ActorA/ActorB
Actor Path represent the name of Actor; create, update, delete will have the same Actor Path

- Actor Selection

Actor Selection is a representation for actor
Actor Selection is created from Actor Path or Actor name
Actor Selection stays valid even when the Actor dies and another instance is created
Actor Selection does not represent a single instance of Actor; it represents a Path
