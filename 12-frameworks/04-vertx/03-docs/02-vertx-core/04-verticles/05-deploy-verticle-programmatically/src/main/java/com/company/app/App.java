package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/*
http://vertx.io/docs/vertx-core/java/#_deploying_verticles_programmatically
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

        // NOTE: Deploying verticles programmatically
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
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
