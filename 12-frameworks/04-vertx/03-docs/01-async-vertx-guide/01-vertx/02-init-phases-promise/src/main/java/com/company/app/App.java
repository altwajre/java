package com.company.app;

import io.vertx.core.Future;

class MyPromise {
    private Future<Void> task1() {
        Future<Void> future = Future.future();
        System.out.println("task1");
        future.complete();
        return future;
    }
    private Future<Void> task2() {
        Future<Void> future = Future.future();
        System.out.println("task2");
        future.complete();
        return future;
    }
    public void run(Future<Void> startFuture){
        Future<Void> steps = task1().compose(v -> task2());
        steps.setHandler(ar -> {
            if(ar.succeeded()) {
                System.out.println("pass");
                startFuture.complete();
            }
            else {
                System.out.println("fail");
                startFuture.fail(ar.cause());
            }
        });
    }
}

/*
Wiki verticle initialization phases

To make code cleaner, adopt return a future/promise object pattern to notify when each of the phases completes, and
whether successful or not.
 */
public class App
{
    public static void main( String[] args )
    {
        MyPromise promise = new MyPromise();
        promise.run(Future.future());
    }
}
/*
output:
task1
task2
pass
 */
