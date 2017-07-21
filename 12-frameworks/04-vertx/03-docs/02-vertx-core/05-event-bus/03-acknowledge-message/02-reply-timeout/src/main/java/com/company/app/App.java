package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

/*
Sending with timeouts

When sending a message with a reply handler you can specify a timeout in the DeliveryOptions.

If a reply is not received within that time, the reply handler will be called with a failure.

The default timeout is 30 seconds.
 */
class Receiver1Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": Receiver1Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName()
          + ": Receiver1Verticle eventBus.consumer() message=" + message.body());

      vertx.setTimer(800, t -> {

        // NOTE: reply with timeout
        DeliveryOptions options = new DeliveryOptions();
        options.setSendTimeout(1000);

        message.reply("reply from Receiver1Verticle", options, ar -> {
          if (ar.failed()) {
            System.out.println(Thread.currentThread().getName()
                + ": ## Receiver1Verticle message.reply() handler failed");
          } else {
            System.out.println(Thread.currentThread().getName()
                + ": ## Receiver1Verticle message.reply() handler succeeded");
          }
        });
      });
    });

  }
}

class Receiver2Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": Receiver2Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName()
          + ": Receiver2Verticle eventBus.consumer() message=" + message.body());
    });

  }
}

class SenderVerticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": SenderVerticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // Setting headers on messages
    DeliveryOptions options = new DeliveryOptions();
    options.addHeader("some-header", "some-value");

    eventBus.send("com.company.msg", "Hello from eventBus.send()", options, ar -> {
      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName()
            + ": ## SenderVerticle received reply - " + ar.result().body());
      }
    });

  }
}

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new Receiver1Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName()
          + ": vertx.deployVerticle() Receiver1Verticle handler");
    });

    vertx.deployVerticle(new Receiver2Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName()
          + ": vertx.deployVerticle() Receiver2Verticle handler");
    });

    vertx.setTimer(800, t -> {
      vertx.deployVerticle(new SenderVerticle(), ar -> {
        System.out.println(Thread.currentThread().getName()
            + ": vertx.deployVerticle() SenderVerticle handler");
      });
    });

  }
}
/*
output:
vert.x-eventloop-thread-0: Receiver1Verticle.start() is called
vert.x-eventloop-thread-1: Receiver2Verticle.start() is called
vert.x-eventloop-thread-3: vertx.deployVerticle() Receiver2Verticle handler
vert.x-eventloop-thread-4: vertx.deployVerticle() Receiver1Verticle handler
vert.x-eventloop-thread-5: SenderVerticle.start() is called
vert.x-eventloop-thread-2: vertx.deployVerticle() SenderVerticle handler
vert.x-eventloop-thread-0: Receiver1Verticle eventBus.consumer() message=Hello from eventBus.send()
vert.x-eventloop-thread-5: ## SenderVerticle received reply - reply from Receiver1Verticle
vert.x-eventloop-thread-0: ## Receiver1Verticle message.reply() handler failed

Click Stop to stop debug
 */
