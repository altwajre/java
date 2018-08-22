# Defining the actor's behavior and state

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/94547588-7665-427e-ba2a-488e850ff4df.xhtml

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/97b30798-ed6a-4370-94af-201d34d05f78.xhtml

## SummingActor

The state is the variable sum 
The behavior is adding of the integer to the sum as soon as the message arrives

## Sending messages to actors (tell-and-forget pattern)

Actors have methods to communicate with each other actors like tell(!) or ask (?) 
where the first one is fire and forget and the second returns a Future which means the response will come from that actor in the future.

As soon as you send the message to the actor, it receives the message, picks up an underlying Java thread from the thread pool, does it's work, and releases the thread.
The actors never block your current thread of execution, thus they are asynchronous by nature.
