# Actor Components and Lifecycle

## Actor components

- Actor Instance - contains state and behavior
- Mail Box - connects sender and receiver
- Dispatcher - engine of machine
- ActorRef - is interface of Actor 

```
                   {Outside World} -send-> [ActorRef]
                                      Dispatch |
           ---- Publish Message ---- [Message Dispatcher]
          |                                    |
   [Message Queue] <- Retrieves Message - [Mail Box] - Invoke -> [Actor]
```

- Actor lifecycle
