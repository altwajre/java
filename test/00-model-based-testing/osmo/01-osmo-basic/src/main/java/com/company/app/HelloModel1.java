package com.company.app;

import osmo.tester.annotation.TestStep;

public class HelloModel1 {
  @TestStep("hello")
  public void sayHello() {
    System.out.println("HELLO");
  }
}
