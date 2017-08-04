package com.company.app;

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
public class ChainAsyncTest {
  private Vertx vertx;

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

    System.out.println("#Before");

    vertx = Vertx.vertx();

    vertx.deployVerticle(HelloVerticle.class.getName(),
/*
The async is completed when the Handler exits, unless new asyncs were created during the invocation,
which can be handy to chain asynchronous behaviors:

Async async = context.async();
vertx.deployVerticle("my.verticle", ar1 -> {
  if (ar1.succeeded()) {
    vertx.deployVerticle("my.otherverticle", ar2 -> {
      if (ar2.succeeded()) {
        async.complete();
      } else {
        context.fail(ar2.cause());
      }
    });
  } else {
    context.fail(ar1.cause());
  }
});

// Can be replaced by

vertx.deployVerticle("my.verticle", context.asyncAssertSuccess(id ->
        vertx.deployVerticle("my_otherverticle", context.asyncAssertSuccess())
));
 */
        context.asyncAssertSuccess(x -> {
          System.out.println("HelloVerticle is deployed");
          vertx.deployVerticle(HiVerticle.class.getName(), context.asyncAssertSuccess( y -> {
            System.out.println("HiVerticle is deployed");
          }));
        }));

  }

  @Test
  public void webClientCallVerticles(TestContext context) {

    String host = context.get("host");
    System.out.println("#webClientCallVerticles: host=" + host);

    WebClient client = WebClient.create(vertx);

    Async async1 = context.async();

    client
        .get(8080, host, "/")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();

            context.assertEquals(200, response.statusCode());

            String body = response.body().toString();
            System.out.println("body: " + body);
            String expected = "Hello from server";
            context.assertEquals(expected, body);

            async1.complete();
          } else {

            context.fail(ar.cause());
          }
        });

    Async async2 = context.async();

    client
        .get(8081, host, "/")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();

            context.assertEquals(200, response.statusCode());

            String body = response.body().toString();
            System.out.println("body: " + body);
            String expected = "Hello from HiVerticle";
            context.assertEquals(expected, body);

            async2.complete();
          } else {

            context.fail(ar.cause());
          }
        });
  }

}
/*
#Before
HelloVerticle is deployed
HiVerticle is deployed
#webClientCallVerticles: host=localhost
body: Hello from server
body: Hello from HiVerticle
 */
