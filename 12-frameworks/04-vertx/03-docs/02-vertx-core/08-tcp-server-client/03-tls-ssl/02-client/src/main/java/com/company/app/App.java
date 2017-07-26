package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.*;

import java.io.File;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    NetClientOptions options = testTrustAll();
//    NetClientOptions options = testJksOptions();
//    NetClientOptions options = testPfxOptions();


    NetClient client = vertx.createNetClient(options);

    // connect to port and host
    client.connect(1234, "localhost", ar -> {
      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": Connected: send string");

        NetSocket socket = ar.result();

        // write data to a socket
        Buffer buffer = Buffer.buffer("Hello from Client!");
        socket.write(buffer);

      } else {
        System.out.println("Failed to connect: " + ar.cause());
      }
    });
  }

  private static NetClientOptions testTrustAll() {

    return new NetClientOptions()
        .setSsl(true)
        .setTrustAll(true);
  }

  private static NetClientOptions testJksOptions() {
    File file = new File("src/main/resources/tls/client-keystore.jks");

    JksOptions options = new JksOptions()
        .setPath(file.getAbsolutePath())
        .setPassword("wibble");

    return new NetClientOptions()
        .setSsl(true)
        .setKeyStoreOptions(options);
  }

  private static NetClientOptions testPfxOptions() {
    File file = new File("src/main/resources/tls/client-keystore.p12");

    PfxOptions options = new PfxOptions()
        .setPath(file.getAbsolutePath())
        .setPassword("wibble");

    return new NetClientOptions()
        .setSsl(true)
        .setPfxKeyCertOptions(options);
  }
}
