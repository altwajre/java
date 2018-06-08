# Overriding the life cycle hooks of an actor

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/b53dee86-61b6-441a-b9c2-e74d29e1cd23.xhtml

we create an actor, which maintains sum as a state, and modify its life cycle hooks. We create this actor under the parent supervisor, which handles the ArthimaticException in the child actor. 

When an actor starts, it calls the preStart method.
When an actor throws an exception, it sends a message to the supervisor, and the supervisor handles the failure by restarting the actor. It clears out the accumulated state of the actor, and creates a fresh new actor, means it then restores the last value assigned to the state of old actor to the preRestart value.

After that postRestart method is called, and whenever the actor stops, the supervisor calls the postStop.
