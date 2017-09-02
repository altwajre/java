package com.company.app;

public interface StateMachineTraverser<S, E> {
  // Returns the initial state as defined when the state machine was constructed.
  S initial();

  // Returns true if the state machine is in an acceptable (aka "final") state.
  boolean accepts(S current);

  // Returns true if the given event has a transition from the current state.
  boolean canTransition(S current, E event);

  // Returns the next state based on the current state and requested transition event.
  S next(S current, E event);
}
