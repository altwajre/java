package com.company.app;

import io.netty.util.CharsetUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
Message Codecs

===

You can send any object you like across the event bus if you define and register a message codec for it.

Message codecs have a name and you specify that name in the DeliveryOptions when sending or publishing the message:

eventBus.registerCodec(myCodec);

DeliveryOptions options = new DeliveryOptions().setCodecName(myCodec.name());

eventBus.send("orders", new MyPOJO(), options);

---

If you always want the same codec to be used for a particular type then you can register a default codec for it, then you don’t have to specify the codec on each send in the delivery options:

eventBus.registerDefaultCodec(MyPOJO.class, myCodec);

eventBus.send("orders", new MyPOJO());
 */
@Data
@AllArgsConstructor
class Person {
  private String name;
  private Integer age; // TODO: convert integer to byte[]
}

class PersonEncoder implements MessageCodec<Person, Person> {

  @Override
  public void encodeToWire(Buffer buffer, Person person) {
    byte[] nameBytes = person.getName().getBytes(CharsetUtil.UTF_8);
    buffer.appendInt(nameBytes.length);
    buffer.appendBytes(nameBytes);
  }

  @Override
  public Person decodeFromWire(int pos, Buffer buffer) {
    int length = buffer.getInt(pos);
    pos += 4;
    byte[] bytes = buffer.getBytes(pos, pos + length);
    String name = new String(bytes, CharsetUtil.UTF_8);
    return new Person(name, 18);
  }

  @Override
  public Person transform(Person person) {
    return new Person(person.getName(), 18);
  }

  @Override
  public String name() {
    return "PersonEncoder";
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }
}

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

//    registerCodec();

    registerDefaultCodec();

  }

  private void registerDefaultCodec() {
    EventBus eventBus = vertx.eventBus();

    MessageCodec codec = new PersonEncoder();
    // NOTE: registerCodec
    eventBus.registerDefaultCodec(Person.class, codec);

    eventBus.send("com.company.msg", new Person("Tom", 28), ar -> {
      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName()
            + ": ## SenderVerticle received reply - " + ar.result().body());
      }
    });
  }

  private void registerCodec() {
    EventBus eventBus = vertx.eventBus();

    MessageCodec codec = new PersonEncoder();
    // NOTE: registerCodec
    eventBus.registerCodec(codec);

    DeliveryOptions options = new DeliveryOptions().setCodecName(codec.name());

    eventBus.send("com.company.msg", new Person("Tom", 28), options, ar -> {
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
vert.x-eventloop-thread-0: Receiver1Verticle eventBus.consumer() message=Person(name=Tom, age=18)
vert.x-eventloop-thread-5: ## SenderVerticle received reply - reply from Receiver1Verticle
vert.x-eventloop-thread-0: ## Receiver1Verticle message.reply() handler

Click Stop to stop debug
 */
