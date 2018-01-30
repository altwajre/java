package com.company.app;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

public class Whisky {
  private static final AtomicInteger COUNTER = new AtomicInteger();
  @Getter
  private final int id;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private String origin;

  public Whisky(String name, String origin) {
    this.id = COUNTER.getAndIncrement();
    this.name = name;
    this.origin = origin;
  }

  public Whisky() {
    this.id = COUNTER.getAndIncrement();
  }
}
