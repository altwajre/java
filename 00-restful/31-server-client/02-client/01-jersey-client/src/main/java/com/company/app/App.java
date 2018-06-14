package com.company.app;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class App
{
    public static void main( String[] args )
    {
        test_sync_invoke();

        test_async_invoke();

    }

    // Note: async call takes some time to end the program which is ok.
    private static void test_async_invoke() {
        Future<Response> future = ClientBuilder.newClient()
                .target("http://localhost:8080/contacts")
                .request()
                .async()
                .get();

        Response response = null;
        try {
            response = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        response.bufferEntity();
        String result = response.readEntity(String.class);
        System.out.println(result);
    }
/*
output:
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
 */

    private static void test_sync_invoke() {
        Response response = ClientBuilder.newClient()
                .target("http://localhost:8080/contacts")
                .request()
                .get();
        response.bufferEntity();
        String result = response.readEntity(String.class);
        System.out.println(result);
    }
/*
output:
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
 */
}
