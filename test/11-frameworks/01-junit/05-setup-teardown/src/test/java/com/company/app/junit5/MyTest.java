package com.company.app.junit5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MyTest extends TestBase {
  @BeforeAll
  public static void initAll() {
    System.out.println("Junit5 MyTest BeforeAll will override the base class @BeforeAll");
  }

  @Test
  public void fooTest() {
    System.out.println("MyTest.fooTest()");
  }
}
