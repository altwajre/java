package com.company.app;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Offer {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private int id;
  private String name;
  private Product product;

  public Offer(String name) {
    this();
    this.name = name;
  }

  public Offer() {
    this.id = COUNTER.getAndIncrement();
  }
}
