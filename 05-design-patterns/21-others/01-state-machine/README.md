# StateMachine

Definition transitions between states

> OrderStateMachine - State Transition Paths

```
State.CREATED can go to following states
     -> Event.PURCHASE -> State.READY
     -> Event.CANCEL   -> State.CANCELLED
==========================================
                 State.CREATED
                 /           \
       Event.PURCHASE    Event.CANCEL
              /               \
      State.READY         State.CANCELLED
     ======================================

   From\To |   CREATED |    (READY)| CANCELLED 
-----------+-----------+-----------+-----------
  [CREATED]|           |  PURCHASE |    CANCEL 
-----------+-----------+-----------+-----------
     READY |           |           |           
-----------+-----------+-----------+-----------
 CANCELLED |           |           |           
 ===============================================
```

> Test

OrderManagerTest
