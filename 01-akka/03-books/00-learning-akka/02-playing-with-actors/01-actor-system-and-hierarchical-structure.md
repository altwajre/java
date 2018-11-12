# Actor System and Hierarchical Structure

- Actor System and hierarchical structure
- Actor components and lifecycle
- Creating actor with Props
- Talking to an Actor
- Supervision and monitoring

## Hierarchical structure for actors

- The actor is an object which encapsulates state and behavior
- The actors communicate by exchanging messages
- Actors like persons

```
     [Project Owner]------>[Project Manager]<------[Client]
                                   |
                                   v
                         --------------------
                         |                  |
                         v                  v
                   [Team Leader]       [Team Leader]
                    |                   |
                    |->[Designer]       |->[Designer]
                    |                   |
                    |->[QA]             |->[QA]
                    |                   |
                    |->[Developer]      |->[Developer]
```

```
                               [Actor]
                                  |
                                  v
                        -----------------------
                        |      |       |      |
                        v      v       v      v
                    [child] [child] [child] [child]  
```

## What's the Actor System

- The Actor System is our root on actors structure
- The Actor System as a collaborating failure ensemble of actors is the natural unit for managing shared facilities
like scheduling services, configuration, and logging

## Actor System components

```
                  [Configuration]   [Event System]
                            ^          ^
                            |          |
  [Dead Letter Office] <- [ Actor System ] -> [Scheduler]
                            |          |
                            v          v
            [User Guardian Actor]    [System Guardian Actor]
```
