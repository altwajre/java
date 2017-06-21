package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import static org.junit.runner.JUnitCore.runClasses;

/*
The Timeout Rule applies the same timeout to all test methods in a class
 */
public class G_TimeoutTest {

  public static class HasClobalTimeout {
    public static String log = "";

    @Rule
    public final TestRule globalTimeout = Timeout.millis(20);

    @Test
    public void testInfiniteLoop1() throws InterruptedException {
      log += "ran_1 ";
      Thread.sleep(800);
    }

    @Test
    public void testInfiniteLoop2() throws InterruptedException {
      log += "ran_2 ";
      Thread.sleep(800);
    }
  }

  @Test
  public void testTimeout() {
    runClasses(HasClobalTimeout.class);
    System.out.println(HasClobalTimeout.log);
  }
}
/*
output:
ran_1 ran_2
 */
