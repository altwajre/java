package com.company.app;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import com.company.app.OrderStateMachine.State;
import com.company.app.OrderStateMachine.Event;

/*
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
 */
public class OrderStateMachine extends DetachedStateMachine<State, Event> {

  private static final Set<State> acceptingStates = EnumSet.of(State.READY);
  private static final Map<State, Map<Event, State>> transitions = new EnumMap<>(State.class);

  static {
    Map<Event, State> transition = new EnumMap<>(Event.class);
    transition.put(Event.PURCHASE, State.READY);
    transition.put(Event.CANCEL, State.CANCELLED);
    transitions.put(State.CREATED, transition);
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
