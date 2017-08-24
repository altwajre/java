package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/*
http://vertx.io/docs/vertx-core/java/#worker_verticles

A worker verticle is just like a standard verticle but it's executed not using an event loop, but using a thread from
the Vert.x worker thread pool.

Worker verticles are designed for calling blocking code, as they won't block any event loops.

If you don't want to use worker verticle to run blocking code, you can also run inline blocking code directly while on
an event loop.

If you want to deploy a verticle as a worker verticle you do that with new DeploymentOptions().setWorker(true)

Worker verticle instances are never executed concurrently by Vert.x by more than one thread, but can executed by
different threads at different times.
 */
class WorkerVerticle extends AbstractVerticle {

    // Called when verticle is deployed
    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() + ": WorkerVerticle.start() is called");
        vertx.runOnContext(v -> {
            System.out.println(Thread.currentThread().getName() + ": vertx.runOnContext() handler end");
        });
    }

    // Optional - called when verticle is undeployed
    @Override
    public void stop() {
        System.out.println(Thread.currentThread().getName() + ": WorkerVerticle.stop() is called");
    }

}

public class App
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();

        // NOTE: set Worker-Verticle to run on Vert.x worker thread pool.
        DeploymentOptions options = new DeploymentOptions().setWorker(true);

        vertx.deployVerticle(new WorkerVerticle(), options, ar -> {
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
vert.x-worker-thread-0: WorkerVerticle.start() is called
vert.x-worker-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-0: vertx.deployVerticle() handler
vert.x-worker-thread-1: WorkerVerticle.stop() is called
vert.x-eventloop-thread-0: vertx.undeploy() handler
 */
