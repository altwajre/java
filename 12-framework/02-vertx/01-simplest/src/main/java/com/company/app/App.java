package com.company.app;

import io.vertx.core.Vertx;

public class App {
    public static void main(String... args) {
        Vertx.vertx()
                .createHttpServer()
                .requestHandler(req -> req.response().end("Hello world"))
                .listen(8080, handler -> {
                    if (handler.succeeded()) {
                        System.out.println("http://localhost:8080/");
                    } else {
                        System.out.println("Failed to listen to port 8080");
                    }
                });
    }
}
/*
1, run it
2, go to http://localhost:8080/
 */