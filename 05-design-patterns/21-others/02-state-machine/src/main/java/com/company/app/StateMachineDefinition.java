package com.company.app;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class StateMachineDefinition<S extends Enum<S>, E extends Enum<E>> {

  private final Set<S> acceptable;
  private final Map<S, Map<E, S>> transitions;
  private int cellWidth = 0;
  private final S initial;
  private final Class<S> stateClazz;
  private Class<E> eventClazz;

  /**
   * Generic State Machine
   *
   * <p>Monitors a specified state machine, ensuring legal transitions.
   *
   * @param initial starting state
   * @param transitions map of state-to-state transitions based on events
   * @param acceptable set of states that indicate an "acceptable" condition
   * @throws NullPointerException initial state can not be null
   */
  public StateMachineDefinition(S initial, Map<S, Map<E, S>> transitions, Set<S> acceptable) {
    Objects.requireNonNull(initial, "Null argument passed for initial state.");
    this.initial = initial;
    stateClazz = (Class<S>) initial.getClass();
    this.acceptable = acceptable == null ? new HashSet<>() : acceptable;
    if (transitions != null) {
      this.transitions = transitions;

      // looking for any event in the transition table to get class type,
      // then stop.
      for (Map<E, S> eventTransition : transitions.values()) {
        if (eventTransition != null) {
          for (E event : eventTransition.keySet()) {
            eventClazz = (Class<E>) event.getClass();
          }
          if (eventClazz != null) {
            break;
          }
        }
      }
    } else {
      this.transitions = new EnumMap<>(stateClazz);
      for (S state : stateClazz.getEnumConstants()) {
        this.transitions.put(state, new HashMap<>());
      }
    }
  }

  /**
   * Returns set of acceptable states.
   *
   * @return acceptable states
   */
  public Set<S> acceptable() {
    return Collections.unmodifiableSet(acceptable);
  }

  /**
   * Returns map of all fsm transitions
   *
   * @return transitions states
   */
  public Map<S, Map<E, S>> transitions() {
    return Collections.unmodifiableMap(transitions);
  }

  private int cellWidth() {
    if (cellWidth == 0) {
      int maxWidth = 0;
      for (S state : stateClazz.getEnumConstants()) {
        if (state.toString().length() > maxWidth) {
          maxWidth = state.toString().length();
        }
      }
      if (eventClazz != null) {
        for (E event : eventClazz.getEnumConstants()) {
          if (event.toString().length() > maxWidth) {
            maxWidth = event.toString().length();
          }
        }
      }
      cellWidth = maxWidth;
    }
    return cellWidth;
  }

  /**
   * Returns initial state.
   *
   * @return initial state
   */
  public S initial() {
    return initial;
  }

  public String print(S current) {
    StringBuilder result = new StringBuilder(super.toString());

    String cornice = "From\\To";
    int width = cornice.length() > cellWidth() ? cornice.length() : cellWidth();
    String cellFormat = "%" + Integer.toString(width + 1) + "s%c";
    String separatorCellLine = new String(new char[width + 2]).replace('\u0000', '-');
    S[] enumConstants = stateClazz.getEnumConstants();
    String separatorLine = separatorCellLine
        + new String(new char[enumConstants.length])
        .replace("\0", "+" + separatorCellLine)
        + "\n";

    // header
    result.append(String.format("%n" + cellFormat, cornice, ' '));
    for (S state : enumConstants) {
      result.append('|');
      char open = acceptable.contains(state) ? '(' : ' ';
      char close = acceptable.contains(state) ? ')' : ' ';
      result.append(String.format(cellFormat, open + state.toString(), close));
    }
    result.append('\n');

    for (S fromState : enumConstants) {
      // separator line
      result.append(separatorLine);

      // row
      char open = (fromState == initial) ? '[' : (fromState == current) ? '{' : ' ';
      char close = (fromState == initial) ? ']' : (fromState == current) ? '}' : ' ';
      result.append(String.format(cellFormat, open + fromState.toString(), close));
      for (S toState : enumConstants) {
        result.append('|');
        String cell = "";
        if (eventClazz != null) {
          Map<E, S> rowTransitions = transitions.get(fromState);
          if (rowTransitions != null) {
            for (E event : eventClazz.getEnumConstants()) {
              if (rowTransitions.get(event) == toState) {
                cell = event.toString();
                break;
              }
            }
          }
        }
        result.append(String.format(cellFormat, cell, ' '));
      }
      result.append('\n');
    }
    return result.toString();
  }
}
