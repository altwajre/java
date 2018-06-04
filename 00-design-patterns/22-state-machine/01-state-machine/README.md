# StateMachine

Definition transitions between states

> OrderStateMachine - State Transition Paths

```
public class OrderStateMachine extends DetachedStateMachine<State, Event> {
  private static final Set<State> acceptingStates = EnumSet.of(State.READY);
  private static final Map<State, Map<Event, State>> transitions = new EnumMap<>(State.class);
  static {
    Map<Event, State> transition = new EnumMap<>(Event.class);
    transition.put(Event.PURCHASE, State.READY);
    transition.put(Event.CANCEL, State.CANCELLED);
    transitions.put(State.CREATED, transition); // root: State.CREATED
  }
  public OrderStateMachine() {
    super(State.CREATED, transitions, acceptingStates);
  }
  public enum Event {PURCHASE, CANCEL}
  public enum State {
    CREATED,
    READY,
    CANCELLED
  }
}

State Transition Paths:

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
