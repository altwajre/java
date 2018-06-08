# Akka Cookbook

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/

Akka is a toolkit for writing truly concurrent, fault-tolerant, distributed, and scalable applications, which can run for days, months and years without stopping, and can heal themselves in case of failure.
Akka provides a basic unit of abstraction of transparent distribution call actors, which form the basis for writing resilient, elastic, event-driven, and responsive systems.

- Scala up

We want our servers to respond in milliseconds instead of seconds. Akka is here to scale up the application as the load on it increases.

- Fault tolerance

We want our application to run day and night continuously with high availability - Akka is here to build fault tolerance for our application.

- Scale out

We want to run our application on a cluster of multiple machines - Akka is here to scale out our application across the data center.

## Reactive System Properties

- Resilient: Applications that can heal themselves, which means they can recover from failure, and will always be responsive, even in case of failure like if we get errors or exceptions

- Elastic: A system which is responsive under varying amount of workload, that is, the system always remains responsive, irrespective of increasing or decreasing traffic, by increasing or decreasing the resources allocated to service this workload

- Message Driven: A system whose components are loosely coupled with each other and communicate using asynchronous message passing, and which reacts to those messages by taking an action

- Responsive: A system that satisfies the preceding three properties is called responsive

## Actor Properties

- State: An actor has internal state, which is mutated sequentially as messages are processed one by one.

- Behavior: An Actor reacts to messages which are sent to it by applying behavior on it.

- Communication: An actor communicates with other actors by sending and receiving messages to/from them.

- Mailbox: A mailbox is the queue of messages from which an actor picks up the message and processes it.

Actors are message-driven, they are passive and do nothing unless and until you send messages to them Once you send them a message, they pick a thread from the thread pool which is also known as a dispatcher, process the message, and release the thread back to the thread pool.

Actors are also asynchronous by nature; they never block your current thread of execution, and continue to work on another thread.

Actor model: https://en.wikipedia.org/wiki/Actor_model
