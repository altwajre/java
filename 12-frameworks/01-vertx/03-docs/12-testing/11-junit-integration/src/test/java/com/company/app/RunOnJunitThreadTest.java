package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class RunOnJunitThreadTest {
  String expected = "Hello from server";

  private Vertx vertx;

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

    String host = context.get("host");
    System.out.println(Thread.currentThread().getName() + ": Before - host=" + host);

    vertx = Vertx.vertx();

    // Register the context exception handler
    vertx.exceptionHandler(context.exceptionHandler());

    vertx.deployVerticle(HelloVerticle.class.getName(),
        context.asyncAssertSuccess());

  }

  @After
  public void after(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void webClientCallHelloVerticle(TestContext context) {

    String host = context.get("host");
    System.out.println(Thread.currentThread().getName() + ": Test - host=" + host);

    Async async = context.async();

    WebClient client = WebClient.create(vertx);

    client
        .get(8080, host, "/")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();

            context.assertEquals(200, response.statusCode());

            String body = response.body().toString();
            System.out.println(Thread.currentThread().getName() + ": send() callback - body=" + body);
            context.assertEquals(expected, body);

            async.complete();
          } else {

            context.fail(ar.cause());
          }
        });
  }

}
/*
main: Before - host=localhost
main: Test - host=localhost
vert.x-eventloop-thread-2: send() callback - body=Hello from server
 */
