package com.company.app;

import org.testng.annotations.Test;

public class MathTest {

  @Test (groups = {"pri1"})
  public void testAdd() {
    int result = Math.add(5, 3);
    System.out.println(result);
  }

  @Test (groups = {"smoke"})
  public void testSubtract() {
    int result = Math.subtract(8, 3);
    System.out.println(result);
  }

  @Test (groups = {"pri2"})
  public void pri2() {
    System.out.println("pri2");
  }

  @Test (groups = {"pending"})
  public void pendingTest() {
    System.out.println("pending");
  }
}
