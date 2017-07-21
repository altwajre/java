package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;

/*
Acknowledging messages / sending replies

When using send the event bus attempts to deliver the message to a MessageConsumer registered with the event bus.

In some cases it’s useful for the sender to know when the consumer has received the message and "processed" it.

To acknowledge that the message has been processed the consumer can reply to the message by calling reply.

When this happens it causes a reply to be sent back to the sender and the reply handler is invoked with the reply.

Some examples:

- A simple message consumer which implements a service which returns the time of
 the day would ack with a message containing the time of day in the reply body
- A message consumer which implements a persistent queue, might ack with true if
 the message was successfully persisted in storage, or false if not.
- A message consumer which processes an order might ack with true when the order
 has been successfully processed so it can be deleted from the database.

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
        message.reply("reply from Receiver1Verticle", ar -> {
          System.out.println(Thread.currentThread().getName()
              + ": ## Receiver1Verticle message.reply() handler");
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
vert.x-eventloop-thread-4: vertx.deployVerticle() Receiver2Verticle handler
vert.x-eventloop-thread-3: vertx.deployVerticle() Receiver1Verticle handler
vert.x-eventloop-thread-5: SenderVerticle.start() is called
vert.x-eventloop-thread-2: vertx.deployVerticle() SenderVerticle handler
vert.x-eventloop-thread-0: Receiver1Verticle eventBus.consumer() message=Hello from eventBus.send()
vert.x-eventloop-thread-5: ## SenderVerticle received reply - reply from Receiver1Verticle
vert.x-eventloop-thread-0: ## Receiver1Verticle message.reply() handler

Click Stop to stop debug
 */
