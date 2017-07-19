package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/*
Standard verticles are assigned an event loop thread when they are created and start method is called with that event loop.
When you call any other methods that takes a handler on a core API from an event loop then Vert.x will guarantee
that those handlers, when called, will be executed on the same event loop.

This means we can guarantee that all the code in your verticle instance is always executed on the same event loop
(as long as you don't create your own threads and call it!).

This means you can write all the code in your application as single threaded and let Vert.x worrying about the threading
and scaling. No more worrying about synchronized and volatile any more, and you also avoid many other cases of race
conditions and deadlock so prevalent when doing hard-rolled 'traditional' multi-threaded application development.
 */
class StandardVerticle extends AbstractVerticle {

    // Called when verticle is deployed
    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() + ": StandardVerticle.start() is called");
        vertx.runOnContext(v -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.runOnContext() handler end");
        });
    }

    // Optional - called when verticle is undeployed
    @Override
    public void stop() {
        System.out.println(Thread.currentThread().getName() + ": StandardVerticle.stop() is called");
    }

}
public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new StandardVerticle(), ar -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");
            vertx.undeploy(ar.result(), undeploy -> {
                System.out.println(Thread.currentThread().getName() + ": vertx.undeploy() handler");
                // self terminated
                System.exit(0);
            });
        });
    }
}
/*
output:
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
