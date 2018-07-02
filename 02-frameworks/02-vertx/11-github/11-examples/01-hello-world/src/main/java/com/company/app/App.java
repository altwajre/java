package com.company.app;

import io.vertx.core.Vertx;

/*
Intellij Run:
Right click the main class and run

Test:
go to http://localhost:8080/
 */
public class App {
  public static void main(String[] args) {
    Vertx
        .vertx()
        .createHttpServer()
        .requestHandler(req -> req
            .response()
            .end("Hello World from Vert.x!"))
        .listen(8080);
  }
}
