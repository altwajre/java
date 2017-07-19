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

        // Test the type of context
        if(context.isEventLoopContext()) {
            System.out.println("Context attached to Event Loop");
        } else if (context.isWorkerContext()) {
            System.out.println("Context attached to Worker Thread");
        } else if (context.isMultiThreadedWorkerContext()) {
            System.out.println("Context attached to Worker Thread - multi threaded worker");
        } else if (! Context.isOnVertxThread()) {
            System.out.println("Context not attached to a thread managed by vert.x");
        }

        /*
        When several handlers run in the same context, they may want to share data.
        The context object offers methods to store and retrieve data shared in the context.
         */
        context.put("data", "hello");

        context.runOnContext(v -> {

            System.out.println(Thread.currentThread().getName() + ": context.runOnContext(). context share data: data=" + context.get("data"));

        });

        System.out.println(Thread.currentThread().getName() + ": thread END");

        vertx.close();
    }
}
/*
output:
Context attached to Event Loop
main: thread END
vert.x-eventloop-thread-0: context.runOnContext(). context share data: data=hello
 */
