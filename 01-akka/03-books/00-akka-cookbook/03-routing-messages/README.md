# Routing Messages

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/b71c9d68-72e4-46f9-8349-a78d074d2a81.xhtml

## Routing mechanism

- We want to send messages to the actor that is less busy among other actors of the same type, to the actor with lowest number of messages

- We may want to send messages in a round-robin order to actor, send messages one by one to all actors in a loop

- We may want to send a single message to all the actors in the group

- We may want to redistribute the work among actors automatically with the help of some mechanism
