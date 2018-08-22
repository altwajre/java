# Akka Persistence

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/092e32e2-0950-46d8-b27e-e342d6cc6db3.xhtml

## Introduction

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/94e2ccca-9307-4e12-bb3c-9dedbac48fa2.xhtml

Another useful module in the Akka ecosystem is Akka persistence. In some use cases, the state held by stateful actors is essential for your application to behave correctly. 
This kind of application cannot afford to lose that state. This could happen if the actor holding the state gets restarted; 
it is migrated to a different node in a cluster, or your JVM crashes. Akka persistence comes in handy with this task: 
it provides a mechanism to save the state and be able to recover it. Unlike traditional approaches, 
Akka persistence uses event sourcing to achieve this task. In the event sourcing world,
 we never store the actual state but an ordered set of events that happened in the actor to achieve that particular state. 
 This way, we have a log of events that we can use to restore the state of an actor when required. 
 Also, this technique provides a way of understanding how an actor reached its current state since we can analyze and replay each event. 
 In this chapter, we will cover how to make your actors persistent and how to use different data stores to persist these events.
 
## Preparing an actor for persistence

Before we take a look at Akka persistence, let's review the terminology and architectures used in it:

- PersistenceActor: A stateful actor that persists events to the journal. When a persistent actor is started or restarted, 
journaled messages are replayed to that actor so that it can recover the internal state from these messages.

- AsyncWriteJournal: A journal keeps an ordered collection of events that can be sent to a persistent actor. 
An application can control which messages are journaled and which are received by persist actor without being journaled. 
The data store behind the journal is configurable and needs to be decided depending on the needs. 
Akka persistence comes with a LevelDB journal plugin (which uses the local filesystem and is not replicated)

- AtLeastOnceDelivery: A messaging delivery mechanism that ensures at-least-once delivery semantics to destinations.

- Snapshot store: A snapshot store persists snapshots of a persistent actor's. Snapshots are used to speed up recovery time from the journal. 
The data store behind the snapshot store is configurable (the same as the journal) and uses the local filesystem as the default.
