# Sending messages to actors and collecting responses

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/775ee813-75bf-4dc0-9a06-68bb0f5c2c38.xhtml

## How it works

We create a child actor called DoubleActor, which doubles the value it gets.
We create a parent actor, which creates a child actor when it receives a CreateChild message, and maintains it in the list.
When the parent actor receives the message Send, it sends a random number to the child, and the child, in turn, sends a response back to the parent.
 