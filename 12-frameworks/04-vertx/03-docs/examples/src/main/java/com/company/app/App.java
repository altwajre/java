package com.company.app;

import io.vertx.core.Vertx;

// go to http://localhost:8080/ after launched the app
public class App {
  public static void main(String[] args) {
    Vertx
        .vertx()
        .createHttpServer()
        .requestHandler(req -> req
            .response()
            .end("Hello World!"))
        .listen(8080);
  }
}
