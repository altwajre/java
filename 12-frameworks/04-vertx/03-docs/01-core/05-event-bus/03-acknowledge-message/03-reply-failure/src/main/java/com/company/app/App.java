package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

/*
Send Failures

Message sends can fail for other reasons, including:

There are no handlers available to send the message to

The recipient has explicitly failed the message using fail
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
        int failureCode = 123;
        message.fail(failureCode, "reply failure from Receiver1Verticle");

//                message.reply("reply from Receiver1Verticle", ar -> {
//                    System.out.println(Thread.currentThread().getName()
//                        + ": ## Receiver1Verticle message.reply() handler");
//                });
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
      if (ar.failed()) {
        System.out.println(Thread.currentThread().getName()
            + ": ## SenderVerticle received reply failed - body=" + ar.cause());
      } else {
        System.out.println(Thread.currentThread().getName()
            + ": ## SenderVerticle received reply succeeded - body=" + ar.result().body());
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
vert.x-eventloop-thread-3: vertx.deployVerticle() Receiver1Verticle handler
vert.x-eventloop-thread-4: vertx.deployVerticle() Receiver2Verticle handler
vert.x-eventloop-thread-5: SenderVerticle.start() is called
vert.x-eventloop-thread-2: vertx.deployVerticle() SenderVerticle handler
vert.x-eventloop-thread-0: Receiver1Verticle eventBus.consumer() message=Hello from eventBus.send()
vert.x-eventloop-thread-5: ## SenderVerticle received reply failed - body=(RECIPIENT_FAILURE,123) reply failure from Receiver1Verticle

Click Stop to stop debug
 */
