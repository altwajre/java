package com.company.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderStateMachineTest {

  @Test
  public void shouldBeTrueTransitionFromCreatedStateOnPurchaseEvent() {
    OrderStateMachine orderStateMachine = new OrderStateMachine();
    final boolean canTransition = orderStateMachine.canTransition(OrderStateMachine.State.CREATED, OrderStateMachine.Event.PURCHASE);
    assertTrue(canTransition);
    System.out.println(canTransition);
  }

  @Test
  public void shouldBeTrueTransitionFromCreatedStateOnCancelEvent() {
    OrderStateMachine orderStateMachine = new OrderStateMachine();
    final boolean canTransition = orderStateMachine.canTransition(OrderStateMachine.State.CREATED, OrderStateMachine.Event.CANCEL);
    assertTrue(canTransition);
    System.out.println(canTransition);
  }

  @Test
  public void shouldBeFalseTransitionFromCancelledStateOnPurchaseEvent() {
    OrderStateMachine orderStateMachine = new OrderStateMachine();
    final boolean canTransition = orderStateMachine.canTransition(OrderStateMachine.State.CANCELLED, OrderStateMachine.Event.PURCHASE);
    assertFalse(canTransition);
    System.out.println(canTransition);
  }

  @Test
  public void printStates() {
    OrderStateMachine orderStateMachine = new OrderStateMachine();
    System.out.println("CREATED state: " + orderStateMachine.print(OrderStateMachine.State.CREATED));
/*
   From\To |   CREATED |    (READY)| CANCELLED
-----------+-----------+-----------+-----------
  [CREATED]|           |  PURCHASE |    CANCEL
-----------+-----------+-----------+-----------
     READY |           |           |
-----------+-----------+-----------+-----------
 CANCELLED |           |           |
 */
    System.out.println("READY state: " + orderStateMachine.print(OrderStateMachine.State.READY));
/*
   From\To |   CREATED |    (READY)| CANCELLED
-----------+-----------+-----------+-----------
  [CREATED]|           |  PURCHASE |    CANCEL
-----------+-----------+-----------+-----------
    {READY}|           |           |
-----------+-----------+-----------+-----------
 CANCELLED |           |           |
 */
    System.out.println("CANCELLED state: " + orderStateMachine.print(OrderStateMachine.State.CANCELLED));
/*
   From\To |   CREATED |    (READY)| CANCELLED
-----------+-----------+-----------+-----------
  [CREATED]|           |  PURCHASE |    CANCEL
-----------+-----------+-----------+-----------
     READY |           |           |
-----------+-----------+-----------+-----------
{CANCELLED}|           |           |
 */
  }
}