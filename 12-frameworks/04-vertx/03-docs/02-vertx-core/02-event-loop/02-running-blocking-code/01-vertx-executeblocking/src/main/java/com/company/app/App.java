package com.company.app;

import io.vertx.core.Vertx;

/*
http://vertx.io/docs/vertx-core/java/#blocking_code

 */
public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx
            .vertx();

        vertx.<String>executeBlocking(future -> {
            // specify both the blocking code to execute
            // and a result handler to be called back async when the blocking code has been executed
            System.out.println(Thread.currentThread().getName() + ": vertx.executeBlocking() future handler");
            String result =  blockingCode();
            future.complete(result);
        }, ar -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.executeBlocking() AsyncResult=" + ar.result());
        });

        System.out.println(Thread.currentThread().getName() + ": thread END");

        vertx.close();
    }

    private static String blockingCode() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // need to comment out e.printStackTrace();
//            e.printStackTrace();
        }

        return "blocking code is executed";
    }
}
/*
output:
main: thread END
vert.x-worker-thread-0: future handler
vert.x-eventloop-thread-1: AsyncResult=blocking code is executed
 */
