package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

/*

Messages are published to an address. Publishing means delivering the message to all handlers that are registered at
that address.

Consumer1Verticle:
    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer1Verticle eventBus.consumer() message=" + message.body());
    });

Consumer2Verticle:
    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer2Verticle eventBus.consumer() message=" + message.body());
    });

PublishVerticle:
    eventBus.publish("com.company.msg", "Hello from eventBus.publish()");

 */

class Consumer1Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName() + ": Consumer1Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer1Verticle eventBus.consumer() message=" + message.body());
    });

    MessageConsumer<String> consumer = eventBus.consumer("com.company.msg");
    consumer.completionHandler(res -> {
      if (res.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": Consumer1Verticle handler registration has reached all nodes");
      } else {
        System.out.println("Registration failed!");
      }
    });

    // registering handler by using consumer.handler()
    consumer.handler(message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer1Verticle consumer.handler() message=" + message.body());
    });

  }
}

class Consumer2Verticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName() + ": Consumer2Verticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    // registering handler by using eventBus.consumer()
    eventBus.consumer("com.company.msg", message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer2Verticle eventBus.consumer() message=" + message.body());
    });

    MessageConsumer<String> consumer = eventBus.consumer("com.company.msg");
    consumer.completionHandler(res -> {
      if (res.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": Consumer2Verticle handler registration has reached all nodes");
      } else {
        System.out.println("Registration failed!");
      }
    });

    // registering handler by using consumer.handler()
    consumer.handler(message -> {
      System.out.println(Thread.currentThread().getName() + ": Consumer2Verticle consumer.handler() message=" + message.body());
    });

  }
}

class PublishVerticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName() + ": PublishVerticle.start() is called");

    EventBus eventBus = vertx.eventBus();

    eventBus.publish("com.company.msg", "Hello from eventBus.publish()");

  }
}

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    vertx.deployVerticle(new Consumer1Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() Consumer1Verticle handler");
    });

    vertx.deployVerticle(new Consumer2Verticle(), ar -> {
      System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() Consumer2Verticle handler");
    });

    vertx.setTimer(800, t -> {
      vertx.deployVerticle(new PublishVerticle(), ar -> {
        System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() PublishVerticle handler");
      });
    });

  }
}
/*
output:
vert.x-eventloop-thread-0: Consumer1Verticle.start() is called
vert.x-eventloop-thread-1: Consumer2Verticle.start() is called
vert.x-eventloop-thread-0: Consumer1Verticle handler registration has reached all nodes
vert.x-eventloop-thread-1: Consumer2Verticle handler registration has reached all nodes
vert.x-eventloop-thread-3: vertx.deployVerticle() Consumer1Verticle handler
vert.x-eventloop-thread-4: vertx.deployVerticle() Consumer2Verticle handler
vert.x-eventloop-thread-5: PublishVerticle.start() is called
vert.x-eventloop-thread-2: vertx.deployVerticle() PublishVerticle handler
vert.x-eventloop-thread-1: Consumer2Verticle eventBus.consumer() message=Hello from eventBus.publish()
vert.x-eventloop-thread-0: Consumer1Verticle eventBus.consumer() message=Hello from eventBus.publish()
vert.x-eventloop-thread-1: Consumer2Verticle consumer.handler() message=Hello from eventBus.publish()
vert.x-eventloop-thread-0: Consumer1Verticle consumer.handler() message=Hello from eventBus.publish()

click Stop to stop debug
 */
