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

public class App {
  public static void main(String[] args) {
    String expected = "Hello from server";

    Vertx vertx = Vertx.vertx();

    HttpServer server = vertx.createHttpServer();
    WebClient client = WebClient.create(vertx);

    TestSuite
        .create("sharing-host")
        .before(context -> {
          // host is available for all test cases
          context.put("host", "localhost");
        })
        .beforeEach(context -> {

          String host = context.get("host");
          Async async = context.async();

          server
              .requestHandler(request -> {
                request
                    .response()
                    .end(expected);
              })
              // port and host
              .listen(8080, host, ar -> {
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
        .test("WebClient call Server ", context -> {

          String host = context.get("host");

          Async async = context.async();

          client
              .get(8080, host, "/")
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
}
/*
Begin test suite sharing-host
Begin test WebClient call Server
Server listening at 8080
body: Hello from server
Passed WebClient call Server
after
End test suite sharing-host , run: 1, Failures: 0, Errors: 0
 */
