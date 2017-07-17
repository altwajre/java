package com.company.app;

import io.vertx.core.Future;

class FutureTest {
    public void run(Future<Void> future) {
        future.setHandler(ar -> {
            if(ar.succeeded()) {
                System.out.println("pass");
            }
            else {
                System.out.println("fail");
                future.fail(ar.cause());
            }
        });
        // Waiting for future to complete
        future.complete();
    }
}

public class App
{
    public static void main( String[] args )
    {
        FutureTest futureTest = new FutureTest();
        futureTest.run(Future.future());
    }
}
/*
output:
pass
 */
