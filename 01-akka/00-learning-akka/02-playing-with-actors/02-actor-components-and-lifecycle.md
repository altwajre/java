# Actor Components and Lifecycle

## Actor components

- Actor Instance - contains state and behavior
- Mail Box - connects sender and receiver
- Dispatcher - engine of machine
- ActorRef - is interface of Actor

```
                   {Outside World} -send-> [ActorRef]
                                      Dispatch |
                                               v
          ----- Publish Message ---- [Message Dispatcher]
          |                                    |
          v                                    v
   [Message Queue] <- Retrieves Message - [Mail Box] - Invoke -> [Actor]
```

- Actor lifecycle

https://www.safaribooksonline.com/library/view/learning-akka/9781784391836/video2_2.html
