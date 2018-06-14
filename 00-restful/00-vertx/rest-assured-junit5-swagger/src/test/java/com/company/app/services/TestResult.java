package com.company.app.services;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TestResult {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private final int id;
  private String status;
  private String error;

  public TestResult() {
    this.id = COUNTER.getAndIncrement();
  }

  public TestResult(String status, String error) {
    this.id = COUNTER.getAndIncrement();
    this.status = status;
    this.error = error;
  }
}
