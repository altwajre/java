package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/*
http://vertx.io/docs/vertx-core/java/#_writing_verticles

 */
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
