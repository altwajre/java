package com.company.app.junit5;

import org.junit.jupiter.api.BeforeAll;

public class TestBase {
  @BeforeAll
  public static void initAll() {
    System.out.println("Junit5 TestBase BeforeAll");
  }
}
