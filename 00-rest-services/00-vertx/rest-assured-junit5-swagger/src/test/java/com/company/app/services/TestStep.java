package com.company.app.services;

import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TestStep {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private final int id;
  private List<String> steps;

  public TestStep() {
    this.id = COUNTER.getAndIncrement();
  }

  public TestStep(List<String> steps) {
    this.id = COUNTER.getAndIncrement();
    this.steps = steps;
  }
}
