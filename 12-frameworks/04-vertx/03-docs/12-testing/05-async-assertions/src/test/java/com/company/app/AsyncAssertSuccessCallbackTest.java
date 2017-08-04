package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class AsyncAssertSuccessCallbackTest {
  String expected = "Hello from server";

  private Vertx vertx;

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

    String host = context.get("host");
    System.out.println("#Before: host=" + host);

    vertx = Vertx.vertx();

    vertx.deployVerticle(HelloVerticle.class.getName(),
/*
The asyncAssertSuccess method returns an Handler<AsyncResult<T>> instance that acts like Async,
invoking the delegating Handler<T> on success and failing the test on failure with the failure cause.
 */
        context.asyncAssertSuccess(v -> {
          System.out.println("asyncAssertSuccess callback is called");
        }));

  }

  @Test
  public void webClientCallHelloVerticle(TestContext context) {

    String host = context.get("host");
    System.out.println("#Test: host=" + host);

    Async async = context.async();

    WebClient client = WebClient.create(vertx);

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
  }

}
/*
#Before: host=localhost
asyncAssertSuccess callback is called
#Test: host=localhost
body: Hello from server
 */
