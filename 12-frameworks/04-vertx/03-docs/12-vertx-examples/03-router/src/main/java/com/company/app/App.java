package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class App
{
    public static void main( String[] args )
    {

        Vertx vertx = Vertx
            .vertx();
        HttpServer server = vertx
            .createHttpServer();

        Router router = Router.router(vertx);
        router.get("/").handler(context -> {
            context.response()
                .putHeader("Content-Type", "text/html")
                .end("index");
        });
        router.get("/wiki").handler(context -> {
            context.response()
                .putHeader("Content-Type", "text/html")
                .end("wiki uri");
        });

        server.requestHandler(router::accept)
            .listen(8080);

    }
}
/*
http://localhost:8080/
index

http://localhost:8080/wiki
wiki uri
 */
