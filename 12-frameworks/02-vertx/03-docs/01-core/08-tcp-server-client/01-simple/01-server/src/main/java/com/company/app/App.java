package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    NetServer server = vertx.createNetServer();

    server.connectHandler(socket -> {

      // Read data from socket
      socket.handler(buffer -> {
        System.out.println(Thread.currentThread().getName() + ": " + buffer.toString());
      });

      // Close handler
      socket.closeHandler(v -> {
        System.out.println("The socket has been closed");
      });
    });

    // listen at port and host
    server.listen(1234, "localhost", ar -> {
      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": Server is listening on " + server.actualPort());
      } else {
        System.out.println("Failed to bind!");
      }
    });

    vertx.setTimer(5800, l -> {
      server.close(v -> {
        if(v.succeeded()) {
          System.out.println("Server is now closed");
        }
        else {
          System.out.println("Close failed");
        }
      });
    });

  }
}
