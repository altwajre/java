# ActorSystem

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/5b8b189b-53d4-418c-839d-be34e3b6e45a.xhtml

ActorSystem is the home for the actors in which they live, it manages the life cycle of an actor and supervise them. On creation, an ActorSystem start three actors:

- /user - The guardian actor: All user-defined actors are created as a child of the parent actor user, that is, when you create your actor in the ActorSystem, it becomes the child of the user guardian actor, and this guardian actor supervises your actors. If the guardian actor terminates, all your actors get terminated as well.

- /system - The system guardian: In Akka, logging is also implemented using actors. This special guardian shutdowns the logged-in actors when all normal actors have terminated. It watches the user guardian actor, and upon termination of the guardian actor, it initiates its own shutdown.

- / - The root guardian: The root guardian is the grandparent of all the so-called top-level actors, and supervies all the top-level actors. Its purpose is to terminate the child upon any type of exception. It sets the ActorSystem status as terminated if the guardian actor is able to terminate all the child actors successfully.
