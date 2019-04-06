package com.company.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Retry<T> implements BusinessOperation<T> {
  private final BusinessOperation<T> op;
  private final int maxAttempts;
  private final long delay;
  private final AtomicInteger attempts;
  private final List<Exception> errors;

  public Retry(
      BusinessOperation<T> op,
      int maxAttempts,
      long delay
  ) {
    this.op = op;
    this.maxAttempts = maxAttempts;
    this.delay = delay;
    this.attempts = new AtomicInteger();
    this.errors = new ArrayList<>();
  }

  public List<Exception> errors() {
    return Collections.unmodifiableList(this.errors);
  }

  public int attempts() {
    return this.attempts.intValue();
  }

  @Override
  public T perform() {
    do {
      try {
        System.out.println("retry.perform()");
        System.out.println("#attempts: " + attempts.intValue());
        return this.op.perform();
      } catch (Exception e) {
        System.out.println("error occurs");
        this.errors.add(e);

        if (this.attempts.incrementAndGet() >= this.maxAttempts) {
          throw e;
        }

        try {
          Thread.sleep(this.delay);
        } catch (InterruptedException f) {
          //ignore
        }
      }
    }
    while (true);
  }
}
