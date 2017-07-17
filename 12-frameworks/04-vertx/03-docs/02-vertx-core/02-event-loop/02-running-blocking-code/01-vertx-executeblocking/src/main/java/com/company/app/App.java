package com.company.app;

import io.vertx.core.Vertx;

public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx
            .vertx();

        vertx.<String>executeBlocking(future -> {
            // specify both the blocking code to execute
            // and a result handler to be called back async when the blocking code has been executed
            String result = blockingCode();
            future.complete(result);
        }, ar -> {
            System.out.println("AsyncResult: " + ar.result());
        });

        System.out.println("#Main Thread END");

        vertx.close();
    }

    private static String blockingCode() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // need to comment out e.printStackTrace();
//            e.printStackTrace();
        }

        return "executed blocking code";
    }
}
/*
output:
#Main Thread END
AsyncResult: executed blocking code
 */
