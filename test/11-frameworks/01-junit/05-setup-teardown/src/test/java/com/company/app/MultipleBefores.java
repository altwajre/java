package com.company.app;

import org.junit.Before;
import org.junit.Test;

public class MultipleBefores {
  @Before
  public void setup1() {
    System.out.println("setup1");
  }
  @Before
  public void setup2() {
    System.out.println("setup2");
  }
  @Test
  public void test1() {
    System.out.println("test1");
  }
}
