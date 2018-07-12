package com.company.app.math;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MyMath {
  public int add(int x, int y) {
    return x + y;
  }
}

public class MyMathTest {

  @Test(groups = {"P0"})
  public void add_test() {
    MyMath myMath = new MyMath();
    int actual = myMath.add(1, 2);
    int expected = 3;
    assertEquals(expected, actual);
  }

  @Test(groups = {"P1"})
  public void add_negative_test() {
    MyMath myMath = new MyMath();
    int actual = myMath.add(-1, -2);
    int expected = -3;
    assertEquals(expected, actual);
  }

  @Test(groups = {"P2"})
  public void foo_test() {
    System.out.println("foo");
  }

}
