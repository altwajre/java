package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {
      socket.handler(buffer -> {
        System.out.println(buffer.toString());
      });
    });

    server.listen(1234, "localhost", ar -> {
      if (ar.succeeded()) {
        System.out.println("Server is listening on " + server.actualPort());
      } else {
        System.out.println("Failed to bind!");
      }
    });

  }
}
