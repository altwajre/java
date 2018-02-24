package com.company.app;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Customer {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private int id;
  private String name;
  private int age;

  public Customer(String name, int age){
    this();
    this.name = name;
    this.age = age;
  }

  public Customer() {
    this.id = COUNTER.getAndIncrement();
  }
}
