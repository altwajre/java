package com.company.app;

import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static org.junit.Assert.fail;
import static org.junit.runner.JUnitCore.runClasses;

/*
TestWatcher are base classes for Rules that take note of the testing action, without modifying it.
For example, this class will keep a log of each passing and failing test.
 */
public class E_TestWatcherTest {

  public static class WatcherTest {
    public static String watchedLog = "";

    @Rule
    public final TestRule testWatcher = new TestWatcher() {
      @Override
      public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
      }

      @Override
      protected void succeeded(Description description) {
        watchedLog += description.getDisplayName() + " success!\n";
      }

      @Override
      protected void failed(Throwable e, Description description) {
        watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + " fail!\n";
      }

      @Override
      protected void skipped(AssumptionViolatedException e, Description description) {
        watchedLog += description.getDisplayName() + " " + e.getClass().getSimpleName() + "\n";
      }

      @Override
      protected void starting(Description description) {
        super.starting(description);
      }

      @Override
      protected void finished(Description description) {
        super.finished(description);
      }
    };

    @Test
    public void fails() {
      System.out.println("fails()");
      fail();
    }

    @Test
    public void succeeds() {
      System.out.println("succeeds()");
    }

  }

  @Test
  public void testWatchTest() {
    runClasses(WatcherTest.class);
    System.out.println(WatcherTest.watchedLog);
  }

}
/*
output:
succeeds()
fails()
succeeds(com.company.app.E_TestWatcherTest$WatcherTest) success!
fails(com.company.app.E_TestWatcherTest$WatcherTest) AssertionError fail!
 */
