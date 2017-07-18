package com.company.app;

import io.vertx.core.Context;
import io.vertx.core.Vertx;

public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx
            .vertx();

        Context context = vertx.getOrCreateContext();

        /*
        DO NOT call blocking operations directly from an event loop,
        as that would prevent it from doing any other useful work.
         */
        context.runOnContext(v -> {

            blockingCode();

            System.out.println(Thread.currentThread().getName() + ": handler end");
        });

        System.out.println(Thread.currentThread().getName() + ": thread END");

        vertx.close();
    }

    private static void blockingCode() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
main: thread END
Jul 17, 2017 9:48:32 PM io.vertx.core.impl.BlockedThreadChecker
WARNING: Thread Thread[vert.x-eventloop-thread-0,5,main] has been blocked for 2698 ms, time limit is 2000
Jul 17, 2017 9:48:33 PM io.vertx.core.impl.BlockedThreadChecker
WARNING: Thread Thread[vert.x-eventloop-thread-0,5,main] has been blocked for 3702 ms, time limit is 2000
Jul 17, 2017 9:48:34 PM io.vertx.core.impl.BlockedThreadChecker
WARNING: Thread Thread[vert.x-eventloop-thread-0,5,main] has been blocked for 4706 ms, time limit is 2000
vert.x-eventloop-thread-0: handler end
 */
