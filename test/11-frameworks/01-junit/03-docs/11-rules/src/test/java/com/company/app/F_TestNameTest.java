package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;
import static org.junit.runner.JUnitCore.runClasses;

public class F_TestNameTest {
  public static class NameRuleTest {

    @Rule
    public final TestName testName = new TestName();

    @Test
    public void testA() {
      System.out.println(testName.getMethodName());
      assertEquals("testA", testName.getMethodName());
    }

    @Test
    public void testB() {
      System.out.println(testName.getMethodName());
      assertEquals("testB", testName.getMethodName());
    }
  }

  @Test
  public void testNameRule() {
    runClasses(NameRuleTest.class);
  }

}
/*
output:
testA
testB
 */
