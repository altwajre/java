package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

/*
http://vertx.io/docs/vertx-unit/java/#_repeating_test

Repeating test

When a test fails randomly or not often, for instance a race condition, it is convenient to run the same test multiple
times to increase the failure likelihood of the test.

Repeating a test
TestSuite.create("my_suite").test("my_test", 1000, context -> {
  // This will be executed 1000 times
});

When declared, beforeEach and afterEach callbacks will be executed as many times as the test is executed.
 */
public class App {
  public static void main(String[] args) {
//    repeatingTest();

    repeatingWebClientCallServerTest();
  }

  private static void repeatingTest() {

    TestSuite
        .create("my_suite")
        .before(context -> {
          System.out.println("before");
        })
        .test("test_1", 3, context -> {
          System.out.println("testing_1");
        })
        .test("test_2", 3, context -> {
          System.out.println("testing_2");
        })
        .after(context -> {
          System.out.println("after");
        })
        .run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite my_suite
before
Begin test test_1
testing_1
testing_1
testing_1
Passed test_1
Begin test test_2
testing_2
testing_2
testing_2
Passed test_2
after
End test suite my_suite , run: 2, Failures: 0, Errors: 0
 */

  private static void repeatingWebClientCallServerTest() {

    String expected = "Hello from server";

    Vertx vertx = Vertx.vertx();

    HttpServer server = vertx.createHttpServer();
    WebClient client = WebClient.create(vertx);

    TestSuite
        .create("repeatingWebClientCallServerTest")
        .before(context -> {

          Async async = context.async();

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

                  context.fail(ar.cause());
                }
              });

        })
        .test("webclient repeatedly call server ", 3, context -> {

          Async async = context.async();

          client
              .get(8080, "localhost", "/")
              .send(ar -> {
                if (ar.succeeded()) {
                  HttpResponse<Buffer> response = ar.result();

                  context.assertEquals(200, response.statusCode());

                  String body = response.body().toString();
                  System.out.println("body: " + body);
                  context.assertEquals(expected, body);

                  async.complete();
                } else {

                  context.fail(ar.cause());
                }
              });

        })
        .after(context -> {
          System.out.println("after");
          // NOTE: must close vertx
          vertx.close();
        })
        .run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite repeatingWebClientCallServerTest
Server listening at 8080
Begin test webclient repeatedly call server
body: Hello from server
body: Hello from server
body: Hello from server
Passed webclient repeatedly call server
after
End test suite repeatingWebClientCallServerTest , run: 1, Failures: 0, Errors: 0
 */
}
