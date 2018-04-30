package com.company.app.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class MyDisabledTest {
  @Test
  public void fooTest() {
    System.out.println("MyDisabledTest.fooTest()");
  }

}
