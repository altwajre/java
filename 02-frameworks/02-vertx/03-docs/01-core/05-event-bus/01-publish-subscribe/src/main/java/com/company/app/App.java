package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/*

Messages are published to an address. Publishing means delivering the message to all handlers that are registered at
that address.

Subscribe1Verticle:
    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Subscribe1Verticle eventBus.consumer() message=" + message.body());
    });

Subscribe2Verticle:
    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Subscribe2Verticle eventBus.consumer() message=" + message.body());
    });

PublishVerticle:
    eventBus.publish("com.company.msg", "Hello from eventBus.publish()");

 */

class Subscribe1Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": Subscribe1Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName()
          + ": Subscribe1Verticle eventBus.consumer() message=" + message.body());
    });

  }
}

class Subscribe2Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": Subscribe2Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // NOTE: MessageConsumer.completionHandler()
    MessageConsumer<String> consumer = eventBus.consumer("com.company.msg");
    consumer.completionHandler(res -> {
      if (res.succeeded()) {
        System.out.println(Thread.currentThread().getName()
            + ": Subscribe2Verticle handler registration has reached all nodes");
      } else {
        System.out.println("Registration failed!");
      }
    });

    // registering handler by using consumer.handler()
    consumer.handler(message -> {
      System.out.println(Thread.currentThread().getName()
          + ": Subscribe2Verticle consumer.handler() message=" + message.body());
    });

  }
}

class PublishVerticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName()
        + ": PublishVerticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    eventBus.publish("com.company.msg", "Hello from eventBus.publish()");

  }
}

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new Subscribe1Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName()
          + ": vertx.deployVerticle() Subscribe1Verticle handler");
    });

    vertx.deployVerticle(new Subscribe2Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName()
          + ": vertx.deployVerticle() Subscribe2Verticle handler");
    });

    vertx.setTimer(800, t -> {
      vertx.deployVerticle(new PublishVerticle(), ar -> {
        System.out.println(Thread.currentThread().getName()
            + ": vertx.deployVerticle() PublishVerticle handler");
      });
    });

  }
}
/*
output:
vert.x-eventloop-thread-0: Subscribe1Verticle.start() is called
vert.x-eventloop-thread-1: Subscribe2Verticle.start() is called
vert.x-eventloop-thread-0: Subscribe1Verticle handler registration has reached all nodes
vert.x-eventloop-thread-1: Subscribe2Verticle handler registration has reached all nodes
vert.x-eventloop-thread-3: vertx.deployVerticle() Subscribe1Verticle handler
vert.x-eventloop-thread-4: vertx.deployVerticle() Subscribe2Verticle handler
vert.x-eventloop-thread-5: PublishVerticle.start() is called
vert.x-eventloop-thread-2: vertx.deployVerticle() PublishVerticle handler
vert.x-eventloop-thread-1: Subscribe2Verticle eventBus.consumer() message=Hello from eventBus.publish()
vert.x-eventloop-thread-0: Subscribe1Verticle eventBus.consumer() message=Hello from eventBus.publish()
vert.x-eventloop-thread-1: Subscribe2Verticle consumer.handler() message=Hello from eventBus.publish()
vert.x-eventloop-thread-0: Subscribe1Verticle consumer.handler() message=Hello from eventBus.publish()

click Stop to stop debug
 */
