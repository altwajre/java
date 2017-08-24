package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class App
{
    /*
    BodyHandler automatically decodes the body from the HTTP requests (e.g., form submissions),
    which can then be manipulated as Vert.x buffer objects.
     */
    public static void main( String[] args )
    {

        Vertx vertx = Vertx
            .vertx();
        HttpServer server = vertx
            .createHttpServer();

        Router router = Router.router(vertx);

        // Enables the reading of the request body for all routes under "/api/customers".
        router.route("/api/customers*").handler(BodyHandler.create());

        router.post("/api/customers").handler(rc -> {
            String body = rc.getBodyAsString();
            System.out.println(body);
            rc.response()
                .putHeader("content-type", "application/json")
                .end(body);
        });

        server.requestHandler(router::accept)
            .listen(8080, ar -> {
                if(ar.succeeded()){
                    System.out.println("HTTP server running on port 8080");
                }
                else {
                    System.err.println("Could not start a HTTP server");
                }
            });

    }
}
/*
curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"name": "Tom", "age": "18"}'

output:
HTTP server running on port 8080
{"name": "Tom", "age": "18"}
 */
