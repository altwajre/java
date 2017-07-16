package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class App
{
    public static void main( String[] args )
    {

        Vertx vertx = Vertx
            .vertx();

        vertx.setTimer(800, timerHandler -> {
            // This handler will get called once after a specified delay
            System.out.println("One-time Timers fired");
        });

        vertx.setPeriodic(500, id -> {
            // This handler will get called every second
            System.out.println("Periodic Timer fired");
        });

        HttpServer server = vertx
            .createHttpServer();

        server
            .requestHandler(request -> {
                // This handler will be called every time an HTTP request is received at the server
                request.response()
                    .end("hello from event driven");
            })
            .listen(8080);

    }
}
/*
output:
Periodic Timer fired
One-time Timers fired
Periodic Timer fired
Periodic Timer fired
Periodic Timer fired
Periodic Timer fired
Periodic Timer fired
 */
