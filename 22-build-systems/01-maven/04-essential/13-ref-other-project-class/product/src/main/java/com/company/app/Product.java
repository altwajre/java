package com.company.app;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Product {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private int id;
  private String name;

  public Product(String name) {
    this();
    this.name = name;
  }

  public Product() {
    this.id = COUNTER.getAndIncrement();
  }
}
