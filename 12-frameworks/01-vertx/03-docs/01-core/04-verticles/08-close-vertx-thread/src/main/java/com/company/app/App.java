package com.company.app;

/*
http://vertx.io/docs/vertx-core/java/#_causing_vert_x_to_exit

Threads maintained by Vert.x instances are not daemon threads so they will prevent the JVM from exiting.

If you are embedding Vert.x and you have finished with it, you can call close to close it down.

This will shut-down all internal thread pools and close other resources, and will allow the JVM to exit.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

class StandardVerticle extends AbstractVerticle {

    // Called when verticle is deployed
    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() + ": StandardVerticle.start() is called");
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

                vertx.close(); // prefer to use vertx.close() instead of System.exit(0);

            });
        });
    }
}
/*
output:
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
