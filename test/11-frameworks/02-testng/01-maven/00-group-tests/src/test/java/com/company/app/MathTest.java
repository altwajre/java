package com.company.app;

import org.testng.annotations.Test;

public class MathTest {

  @Test (groups = {"pri1"})
  public void testAdd() {
    System.out.println("# pri1");
    int result = Math.add(5, 3);
    System.out.println(result);
  }

  @Test (groups = {"smoke"})
  public void testSubtract() {
    System.out.println("# smoke test");
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
