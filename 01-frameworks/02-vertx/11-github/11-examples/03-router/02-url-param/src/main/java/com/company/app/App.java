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
        router.get("/wiki/:page").handler(context -> {
            String page = context.request().getParam("page");
            System.out.println(page);
            context.response()
                .putHeader("Content-Type", "text/html")
                .end(page);
        });

        server.requestHandler(router::accept)
            .listen(8080);

    }
}
/*
http://localhost:8080/wiki/foo
foo
 */
