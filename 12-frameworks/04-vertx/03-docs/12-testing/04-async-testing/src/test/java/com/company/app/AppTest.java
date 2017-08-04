package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.Test;

/*
1. The callback exits but the test case is not terminated

2. The event callback from the bus terminates the test

Creating an Async object with the async method marks the executed test case as non terminated. The test case terminates
when the complete method is invoked.

NOTE: When the complete callback is not invoked, the test case fails after a certain timeout.

Several Async objects can be created during the same test case, all of them must be completed to terminate the test.

It is possible to wait until the completion of a specific Async, similar to Java’s count-down latch:
WARNING: async.awaitSuccess() should not be executed from the event loop!
 */
public class AppTest {

  @Test
  public void waitThreadCompletedTest() {

    TestSuite suite = TestSuite.create("waitThreadCompletedTest");

    suite.test("terminate test after thread completed", context -> {

      Async async = context.async();
      new Thread(() -> {
        try {
          Thread.sleep(800);
          System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException ignore) {
        } finally {
          async.complete();
        }
      }).start();

      async.awaitSuccess();
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));

  }
/*
Begin test suite waitThreadCompletedTest
Begin test terminate test after thread completed
Thread-2
Passed terminate test after thread completed
End test suite waitThreadCompletedTest , run: 1, Failures: 0, Errors: 0
 */

  @Test
  public void waitVertxTimerCompletedTest() {

    TestSuite suite = TestSuite.create("waitVertxTimerCompletedTest");

    suite.test("terminate test after Vertx callback", context -> {

      Async async = context.async();

      Vertx vertx = Vertx
          .vertx();

      vertx.setTimer(800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": One-time Timers fired");

        vertx.close();

        async.complete();
      });

      async.awaitSuccess();
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite waitVertxTimerCompletedTest
Begin test terminate test after Vertx callback
vert.x-eventloop-thread-0: One-time Timers fired
Passed terminate test after Vertx callback
End test suite waitVertxTimerCompletedTest , run: 1, Failures: 0, Errors: 0
 */

  @Test
  public void waitVertxServerStartedInBeforeTest() {

    String expected = "Hello from server";

    TestSuite suite = TestSuite.create("waitVertxServerStartedInBeforeTest");

    suite.before(context -> {

      System.out.println("#before");

      Async async = context.async();

      Vertx vertx = Vertx
          .vertx();

      HttpServer server = vertx.createHttpServer();

      server
          .requestHandler(request -> {
            request
                .response()
                .end(expected);
          })
          // port and host
          .listen(8080, "localhost", ar -> {
            if (ar.succeeded()) {
              HttpServer result = ar.result();
              System.out.println("Server listening at " + result.actualPort());

              async.complete();

            } else {
              System.out.println("Failed to bind!");
            }
          });

      async.awaitSuccess(1800);

    }).test("Vertx Client calls Server", context -> {

      Async async = context.async();

      Vertx vertx = Vertx.vertx();

      HttpClient client = vertx.createHttpClient();

      client.getNow(8080, "localhost", "/", response -> {
        System.out.println("StatusCode: " + response.statusCode());
        response.bodyHandler(body -> {
          String actual = body.toString();
          System.out.println(actual);

          context.assertEquals(expected, actual);
          async.complete();
        });
      });

      async.awaitSuccess(1800);

    }).after(context -> {
      System.out.println("#after");
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite waitVertxServerStartedInBeforeTest
#before
Server listening at 8080
Begin test Vertx Client
StatusCode: 200
Response body: Hello from server
Passed Vertx Client calls Server
#after
End test suite waitVertxServerStartedInBeforeTest , run: 1, Failures: 0, Errors: 0
 */

  @Test
  public void webClientCallServerTest() {

    String expected = "Hello from server";

    TestSuite suite = TestSuite.create("webClientCallServerTest");

    suite.before(context -> {

      System.out.println("#before");

      Async async = context.async();

      Vertx vertx = Vertx
          .vertx();

      HttpServer server = vertx.createHttpServer();

      server
          .requestHandler(request -> {
            request
                .response()
                .end(expected);
          })
          // port and host
          .listen(8080, "localhost", ar -> {
            if (ar.succeeded()) {
              HttpServer result = ar.result();
              System.out.println("Server listening at " + result.actualPort());

              async.complete();

            } else {
              System.out.println("Failed to bind!");
            }
          });

      async.awaitSuccess(1800);

    }).test("Vertx Client calls Server", context -> {

      Async async = context.async();

      Vertx vertx = Vertx.vertx();

      WebClient client = WebClient.create(vertx);

      client
          .get(8080, "localhost", "/")
          .send(ar -> {
            if (ar.succeeded()) {
              HttpResponse<Buffer> response = ar.result();

              context.assertEquals(200, response.statusCode());

              String actual = response.body().toString();
              context.assertEquals(expected, actual);

              async.complete();
            } else {

              context.fail(ar.cause());
            }
          });

      async.awaitSuccess(1800);

    }).after(context -> {
      System.out.println("#after");
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite webClientCallServerTest
#before
Server listening at 8080
Begin test Vertx Client calls Server
Passed Vertx Client calls Server
#after
End test suite webClientCallServerTest , run: 1, Failures: 0, Errors: 0
 */

  /*
Async can also be created with an initial count value, it completes when the count-down reaches zero using countDown:

Wait until the complete count-down reaches zero

Calling complete() on an async completes the async as usual, it actually sets the value to 0.
 */
  @Test
  public void asyncCountDownTest() {

    TestSuite suite = TestSuite.create("asyncCountDownTest");

    suite.test("test async.CountDown()", context -> {

      Async async = context.async(2);

      Vertx vertx = Vertx
          .vertx();

      vertx.setTimer(800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": Timer1 fired");

        async.countDown();
      });

      vertx.setTimer(800, v -> {

        // This handler will get called once after a specified delay
        System.out.println(Thread.currentThread().getName() + ": Timer2 fired");

        async.countDown();
      });

      // Wait until completion of the timer and the http request
      async.awaitSuccess(1800);
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite asyncCountDownTest
Begin test test async.CountDown()
vert.x-eventloop-thread-0: Timer1 fired
vert.x-eventloop-thread-1: Timer2 fired
Passed test async.CountDown()
End test suite asyncCountDownTest , run: 1, Failures: 0, Errors: 0
 */
}
