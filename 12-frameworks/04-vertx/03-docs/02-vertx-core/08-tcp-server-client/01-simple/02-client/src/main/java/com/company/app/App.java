package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    NetClient client = vertx.createNetClient();
    client.connect(1234, "localhost", ar -> {
      if (ar.succeeded()) {
        System.out.println("Connected!");
        NetSocket socket = ar.result();
        SocketAddress remoteAddress = socket.remoteAddress();
        System.out.println(remoteAddress);

        Buffer buffer = Buffer.buffer("Hello from Client!");
        socket.write(buffer);
      } else {
        System.out.println("Failed to connect: " + ar.cause());
      }
    });

  }
}
