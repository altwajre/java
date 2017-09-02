package com.company.app;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;
import java.util.Set;

public class DetachedStateMachine<S extends Enum<S>, E extends Enum<E>> implements StateMachineTraverser<S, E> {

  @JsonIgnore
  private final StateMachineDefinition<S, E> definition;

  public DetachedStateMachine(S initial, Map<S, Map<E, S>> transitons, Set<S> acceptable) {
    this.definition = new StateMachineDefinition<>(initial, transitons, acceptable);
  }

  @Override
  public S initial() {
    return definition.initial();
  }

  @Override
  public boolean accepts(S current) {
    return definition.acceptable().contains(current);
  }

  @Override
  public boolean canTransition(S current, E event) {
    return definition.transitions().containsKey(current) && definition.transitions().get(current).containsKey(event);
  }

  @Override
  public S next(S current, E event) {
    if (!canTransition(current, event)) {
      throw new IllegalStateException(
          String.format("Unexpected event, %s, while in state %s.%n", event.toString(),
              current.toString()));
    }
    return definition.transitions().get(current).get(event);
  }

  public String print(S current) {
    return definition.print(current);
  }
}
