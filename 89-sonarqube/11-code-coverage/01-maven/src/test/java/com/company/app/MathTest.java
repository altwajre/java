package com.company.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathTest {
  @Test
  public void add() {
    Math math = new Math();
    assertEquals(3, math.add(1, 2));
  }
}
