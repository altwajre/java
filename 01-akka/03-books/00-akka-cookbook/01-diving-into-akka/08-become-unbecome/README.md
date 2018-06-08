# Become/unbecome behavior of an actor

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/b8fb1e07-6d4f-4d99-9289-7f91f658a2b6.xhtml

In some situations, we want our actor to change its behavior based on its state. 
There are cases where an actor receives a message, and if its state changes or transitions, it changes the way further messages should be handled.

Using become/unbecome, we can host swap the actor functionality at runtime.

> How it works

define an actor which changes its state to handle string and integer values.
If the state is true, the behavior as context.become(isStateTrue), and it starts handling string messages.
If the state is false, the behavior as context.become(isStateFalse), and it starts handling integer messages.
