# ask pattern

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/134f72ce-2c7b-4e7e-95b9-d98e7f4cf250.xhtml

## Send result back to the sender

In the actor receive block, we send the Fibonacci result back to the sender. 
Actors, by nature, known who has sent them the message, thus we always have the sender present in the context of the receive block.

When you send a message to the actor using a question mark (?), it returns a future promising that you will get the result when the operation is completed.
