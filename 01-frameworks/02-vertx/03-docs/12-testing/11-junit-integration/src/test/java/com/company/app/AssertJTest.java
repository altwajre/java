package com.company.app;

import io.vertx.core.Handler;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(VertxUnitRunner.class)
public class AssertJTest {
  String expected = "Hello from server";

  private Vertx vertx;

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

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

//            context.assertEquals(200, response.statusCode());
            assertThat(response.statusCode()).isEqualTo(200);

            String body = response.body().toString();
            System.out.println(Thread.currentThread().getName() + ": send() callback - body=" + body);
            context.assertEquals(expected, body);

            async.complete();
          } else {

            context.fail(ar.cause());
          }
        });
  }
/*
main: Test - host=localhost
vert.x-eventloop-thread-2: send() callback - body=Hello from server
 */

  @Test
  public void testAsyncOperation(TestContext context) {

    Async async = context.async();

    getSomeItems(list -> {
      System.out.println(list);
      assertThat(list).contains("Tom", "Dick");
      async.complete();
    });

  }
/*
[Tom, Dick, Harry]
 */

  private void getSomeItems(Handler<List<String>> handler) {
    vertx.setTimer(800, l -> handler.handle(Arrays.asList("Tom", "Dick", "Harry")));
  }
}
