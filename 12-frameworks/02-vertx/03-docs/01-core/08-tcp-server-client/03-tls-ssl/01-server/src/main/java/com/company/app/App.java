package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.PfxOptions;

import java.io.File;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

//    NetServerOptions options = testJksOptions();
    NetServerOptions options = testPfxOptions();

    NetServer server = vertx.createNetServer(options);
    server.connectHandler(socket -> {
      System.out.println(Thread.currentThread().getName() + ": server.connectHandler()");

      socket.handler(buffer -> {
        System.out.println(Thread.currentThread().getName() + ": socket.handler() " + buffer.toString());
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

  }

  private static NetServerOptions testJksOptions() {
    File file = new File("src/main/resources/tls/server-keystore.jks");

    JksOptions options = new JksOptions()
        .setPath(file.getAbsolutePath())
        .setPassword("wibble");

    return new NetServerOptions()
        .setSsl(true)
        .setKeyStoreOptions(options);
  }

  private static NetServerOptions testPfxOptions() {
    File file = new File("src/main/resources/tls/server-keystore.p12");

    PfxOptions options = new PfxOptions()
        .setPath(file.getAbsolutePath())
        .setPassword("wibble");

    return new NetServerOptions()
        .setSsl(true)
        .setPfxKeyCertOptions(options);
  }
}
