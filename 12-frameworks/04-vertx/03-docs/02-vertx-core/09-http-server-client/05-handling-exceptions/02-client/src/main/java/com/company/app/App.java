package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;

public class App
{
    public static void main( String[] args )
    {

        Vertx vertx = Vertx.vertx();

        HttpClient client = vertx.createHttpClient();

        HttpClientRequest request = client.request(HttpMethod.GET, 8080, "localhost", "HttpMethodGET", response -> {
            System.out.println("StatusCode: " + response.statusCode());
            response.bodyHandler(body -> {
                System.out.println("Response body: " + body.toString());
            });
        });

        // Handling exceptions
        request.exceptionHandler(e -> {
            System.out.println("Received exception: " + e.getMessage());
            e.printStackTrace();
        });
        request.end();
    }
}
