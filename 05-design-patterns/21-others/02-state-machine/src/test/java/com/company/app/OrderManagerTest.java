package com.company.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class OrderManagerTest {
  @Test
  public void testOrderManager() {
    Order order = new Order();
    order.orderId = 18;
    OrderManager orderManager = new OrderManager();
    final Order purchasedOrder = orderManager.purchase(order);
    assertEquals(OrderStateMachine.State.READY, purchasedOrder.getOrderState());

    System.out.println(orderManager.getOrderStateMachine().print(OrderStateMachine.State.CREATED));
  }
}
