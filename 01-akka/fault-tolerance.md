# fault tolerance (resilient)

https://en.wikipedia.org/wiki/Fault_tolerance

- microservices-fault-tolerance

https://vaadin.com/blog/microservices-fault-tolerance

## What is fault tolerance?

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/7cad334b-0dd6-4e64-887f-452ce0ec5e8a.xhtml

Fault tolerance is a property of systems that are intended to be always responsive rather than failing completely in case of a failure.

A fault-tolerant system is one which is continue as more or less fully operational, with perhaps a reduction in throughput or an increase in response time because of partial failure of its components.

Even if a components fails, the whole system never gets shut down; instead, it remains operational and responsive with just a decreased throughput.

Similarly, while designing a distributed system, we need to care about what would happen if one or more it's components go down. So, the system design should itself be such that the system is able to take appropriate action to resolve the issue.

Before designing such a system, we need to keep the following things in mind:

- Breaking the system into components: While designing a fault-tolerant system, the first requirement is to break the system into parts, that is, components which are each responsible for some functionality. Certain failure in one component of the system should not interfere with the other parts of the system, and should not bring cascading failures in the system.

- Focus on the important components of the system: There are some parts that are important for a system to have. Such parts should run without interference from the failing parts to avoid inaccurate results.

- Backup of important components: It is recommended to have a backup of components so that in case of failure, similar components can ensure the high availability of the system.

In general, the following are some of the ways to build fault tolerance for systems:

- Duplication: The purpose of duplication is to run multiple identical instances of system components so that if a failure occurs, other instances will be a available to process the request

- Replication: The purpose of replication is to provide multiple identical instances of the components (hardware and software) and to send a direct request to all of them one of the results is then chosen from among them based on them

- Isolation: The purpose of isolation is to keep the components running in different processes, and communicate through passing of messages to ensure isolation of concerns and loose coupling between them. This means that one should not be affected because of another's failure.

- Delegation: The purpose of delegation is to hand over the processing responsibility of a task to another component so that the delegating component can perform other processing, or optionally observe the progress of the delegated task in case additional action, such as handling failure or reporting progress is required.

> What is a component?

All of us know what a module is in software architecture. A module is a piece of a software that contains a particular functionality to do a certain task. We cannot say that a component is synonymous with module. Instead, a component is a self-contained, isolated, and encapsulated entity, which means that it does not affect other components. Different components can use the same module, but their own behavior is a module of its own. Asynchronous message passing is a way to build a communication layer between them.

Microservices rely on component-based architecture. Microservices are small, independent, decoupled process running in isolation from each other to build a complex system. We can scale each component/Microservice according to workload.

> How Akka fits in between all of them?

Akka as a toolkit provides a way to build fault tolerance for applications.

- The Akka supervisor actor can resume, stop, restart, or terminate the execution of the supervised actor, thus providing a way for fault isolation
- Akka provides parent-child model actor, so we can build a tree-like hierarchical structure of our application
- We can create a duplicate actor in case of failure, and replace the faulty one using supervisor strategies
- Akka actors, as components, have a life cycle, you can start, stop, restart and kill them, that is, you can destroy an actor after it has done some work, or if it fails
- Asynchronous message passing allows us to put a boundary between two actors so as to separate the concerns of the different components

Akka is built keeping the above things in mind. It uses the let-it-crash model to build fault-tolerant applications.

Akka lets actors crash and separate business logic from error/exception handling logic. It uses the delegation model to delegate the responsibility of error/exception handling to the supervisor of the actor.
