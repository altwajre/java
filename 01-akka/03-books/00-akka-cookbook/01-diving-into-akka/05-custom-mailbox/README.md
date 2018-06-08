# Custom mailbox

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/c5a311c4-cb0c-4681-abca-46abdf66e529.xhtml

Each actor has its own mailbox-like queue from which it picks up the messages one by one, and processes them.

There might be a situation when you want to control the way the actor picks up the message or anything else.
We will create an actor mailbox that will accept messages from actors of particular name.
