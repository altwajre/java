package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;
import io.vertx.core.net.SocketAddress;

import java.io.File;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    sendString(vertx);

    sendFile(vertx);

  }

  private static void sendFile(Vertx vertx) {

    NetClient client = vertx.createNetClient();

    // connect to port and host
    client.connect(1234, "localhost", ar -> {
      if (ar.succeeded()) {

        System.out.println(Thread.currentThread().getName() + ": Connected: send file");

        NetSocket socket = ar.result();
        File file = new File("src/main/resources/json/parking.json");
        socket.sendFile(file.getAbsolutePath());

      } else {
        System.out.println("Failed to connect: " + ar.cause());
      }
    });
  }

  private static void sendString(Vertx vertx) {

    NetClientOptions options = new NetClientOptions()
        .setReconnectAttempts(10)
        .setReconnectInterval(500);

    NetClient client = vertx.createNetClient(options);

    // connect to port and host
    client.connect(1234, "localhost", ar -> {
      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": Connected: send string");

        NetSocket socket = ar.result();
        SocketAddress remoteAddress = socket.remoteAddress();
        System.out.println(remoteAddress);

        // write data to a socket
        Buffer buffer = Buffer.buffer("Hello from Client!");
        socket.write(buffer);

      } else {
        System.out.println("Failed to connect: " + ar.cause());
      }
    });
  }
}
