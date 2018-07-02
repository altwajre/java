package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    for(int i = 0; i < 8; i++){
      NetClient client = vertx.createNetClient();

      // connect to port and host
      client.connect(1234, "localhost", ar -> {
        if (ar.succeeded()) {
          System.out.println(Thread.currentThread().getName() + ": Connected");

          NetSocket socket = ar.result();

          // write data to a socket
          Buffer buffer = Buffer.buffer("Hello from Client_Name=" + Thread.currentThread().getName());
          socket.write(buffer);

        } else {
          System.out.println("Failed to connect: " + ar.cause());
        }
      });
    }

  }
}
/*
output:
vert.x-eventloop-thread-7: Connected
vert.x-eventloop-thread-0: Connected
vert.x-eventloop-thread-4: Connected
vert.x-eventloop-thread-5: Connected
vert.x-eventloop-thread-6: Connected
vert.x-eventloop-thread-3: Connected
vert.x-eventloop-thread-1: Connected
vert.x-eventloop-thread-2: Connected
 */
