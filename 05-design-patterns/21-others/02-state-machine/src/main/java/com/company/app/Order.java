package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Order {
  @JsonProperty
  int orderId;
  @JsonProperty
  private long total;
  @JsonProperty
  private OrderStateMachine.State orderState;

  public Order() {
    orderState = OrderStateMachine.State.CREATED;
  }
}
