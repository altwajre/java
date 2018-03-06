package com.company.app;

import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Whisky {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  private final int id;
  private String name;
  private String origin;
  private State state;
  private List<String> fats;

  public Whisky(String name, String origin, List<String> fats) {
    this.id = COUNTER.getAndIncrement();
    this.name = name;
    this.origin = origin;
    this.fats = fats;
  }

  public Whisky() {
    this.id = COUNTER.getAndIncrement();
  }
}
