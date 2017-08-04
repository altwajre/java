package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.impl.TestSuiteImpl;
import io.vertx.ext.unit.report.ReportOptions;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.Test;

public class TestSuiteTest {

  /*
  Time out
Each test case of a test suite must execute before a certain timeout is reached.
The default timeout is of 2 minutes, it can be changed using test options:
   */
  @Test
  public void timeoutTest() {
    TestSuite suite = TestSuite.create("timeoutTest");

    suite.test("test timeout", context -> {
      Vertx vertx = Vertx
          .vertx();

      Async async = context.async();

      vertx.setTimer(1800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": One-time Timer fired");

        vertx.close();

        async.complete();
      });

      async.awaitSuccess();

    });

    suite.run(new TestOptions().setTimeout(800).addReporter(new ReportOptions().setTo("console")));

  }
/*
Begin test suite timeoutTest
Begin test test timeout
End test suite timeoutTest , run: 1, Failures: 0, Errors: 1
Failed test timeout
java.util.concurrent.TimeoutException
	at io.vertx.ext.unit.impl.TestContextImpl$Step.lambda$run$0(TestContextImpl.java:112)
	at io.vertx.ext.unit.impl.TestContextImpl$Step$$Lambda$16/1551870003.run(Unknown Source)
	at java.lang.Thread.run(Thread.java:745)
 */

  /*
  Event loop
  Vertx Unit execution is a list of tasks to execute, the execution of each task is driven by the completion of the previous task. These tasks should leverage Vert.x event loop when possible but that depends on the current execution context (i.e the test suite is executed in a main or embedded in a Verticle) and wether or not a Vertx instance is configured.

  The setUseEventLoop configures the usage of the event loop:
   */
  @Test
  public void eventLoopTest() {

    TestSuiteImpl suite = (TestSuiteImpl)TestSuite.create("waitVertxTimerCompletedTest");

    Vertx vertx = Vertx
        .vertx();

    suite.test("terminate test after Vertx callback", context -> {

      System.out.println(Thread.currentThread().getName());

      Async async = context.async();

      vertx.setTimer(800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": One-time Timers fired");

        vertx.close();

        async.complete();
      });

      async.awaitSuccess();
    });

    suite.runner()
        .setUseEventLoop(true)
        .setVertx(vertx);

    suite.run(
        new TestOptions()
            .addReporter(new ReportOptions().setTo("console")));
  }
/*
objc[60587]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/bin/java (0x109e9f4c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x109f674e0). One of the two will be used. Which one is undefined.
Begin test suite waitVertxTimerCompletedTest
Begin test terminate test after Vertx callback
main
vert.x-eventloop-thread-0: One-time Timers fired
Passed terminate test after Vertx callback
End test suite waitVertxTimerCompletedTest , run: 1, Failures: 0, Errors: 0
 */

}
