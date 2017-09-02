package com.company.app;

import lombok.Data;

@Data
public class OrderManager {
  private OrderStateMachine orderStateMachine = new OrderStateMachine();

  public Order purchase(Order order) {
    if(order.getOrderState() == OrderStateMachine.State.CREATED) {
      if(orderStateMachine.canTransition(OrderStateMachine.State.CREATED, OrderStateMachine.Event.PURCHASE)) {
        final OrderStateMachine.State currentState = orderStateMachine.next(OrderStateMachine.State.CREATED, OrderStateMachine.Event.PURCHASE);
        order.setOrderState(currentState);
      }
    }
    return order;
  }
}
