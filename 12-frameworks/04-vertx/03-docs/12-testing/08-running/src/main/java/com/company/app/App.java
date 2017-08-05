package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.impl.TestSuiteImpl;
import io.vertx.ext.unit.report.ReportOptions;

public class App {
  public static void main(String[] args) {
    timeoutTest();

    eventLoopTest();
  }

  /*
  Time out
  Each test case of a test suite must execute before a certain timeout is reached.
  The default timeout is of 2 minutes, it can be changed using test options:
   */
  private static void timeoutTest() {
    TestSuite suite = TestSuite.create("timeoutTest");

    Vertx vertx = Vertx.vertx();

    suite.test("test timeout", context -> {

      Async async = context.async();

      vertx.setTimer(1800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": One-time Timer fired");

        vertx.close();

        async.complete();
      });

    }).after(context -> {
      System.out.println("#after");
      // NOTE: must close vertx
      vertx.close();
    });

    suite.run(new TestOptions().setTimeout(800).addReporter(new ReportOptions().setTo("console")));

  }
/*
Begin test suite timeoutTest
Begin test test timeout
Failed test timeout
java.util.concurrent.TimeoutException
	at io.vertx.ext.unit.impl.TestContextImpl$Step.lambda$run$0(TestContextImpl.java:112)
	at io.vertx.ext.unit.impl.TestContextImpl$Step$$Lambda$20/1690287238.run(Unknown Source)
	at java.lang.Thread.run(Thread.java:745)
#after
End test suite timeoutTest , run: 1, Failures: 0, Errors: 1
 */

  /*
  Event loop
  Vertx Unit execution is a list of tasks to execute, the execution of each task is driven by the completion of the previous task. These tasks should leverage Vert.x event loop when possible but that depends on the current execution context (i.e the test suite is executed in a main or embedded in a Verticle) and wether or not a Vertx instance is configured.

  The setUseEventLoop configures the usage of the event loop:
   */
  private static void eventLoopTest() {

    TestSuiteImpl suite = (TestSuiteImpl) TestSuite.create("waitVertxTimerCompletedTest");

    Vertx vertx = Vertx
        .vertx();

    suite.test("terminate test after Vertx callback", context -> {

      System.out.println(Thread.currentThread().getName());

      Async async = context.async();

      vertx.setTimer(800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": One-time Timer fired");

        vertx.close();

        async.complete();
      });

    }).after(context -> {
      System.out.println("#after");
      // NOTE: must close vertx
      vertx.close();
    });

    suite.runner()
        .setUseEventLoop(true)
        .setVertx(vertx);

    suite.run(
        new TestOptions()
            .addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite waitVertxTimerCompletedTest
Begin test terminate test after Vertx callback
main
vert.x-eventloop-thread-0: One-time Timer fired
Passed terminate test after Vertx callback
#after
End test suite waitVertxTimerCompletedTest , run: 1, Failures: 0, Errors: 0
 */
}
