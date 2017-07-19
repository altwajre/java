package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

class StandardVerticle extends AbstractVerticle {

    // Called when verticle is deployed
    @Override
    public void start() {
        System.out.println(Thread.currentThread().getName() + ": StandardVerticle.start() is called");

        // Accessing environment variables in a Verticle
        System.out.println(Thread.currentThread().getName() + ": System.getenv(HOME)=" + System.getenv("HOME"));
        System.out.println(Thread.currentThread().getName() + ": System.getProperty(prop)=" + System.getProperty("os.name"));

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

public class App {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        // NOTE: Deploying verticles programmatically
        vertx.deployVerticle(new StandardVerticle(), ar -> {

            if (ar.succeeded()) {
                System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");

                vertx.undeploy(ar.result(), undeploy -> {
                    System.out.println(Thread.currentThread().getName() + ": vertx.undeploy() handler");
                    vertx.close(); // prefer to use vertx.close() instead of System.exit(0);
                });
            } else {
                System.out.println("Deployment failed");
            }
        });

        System.out.println(Thread.currentThread().getName() + ": thread END");

    }
}
/*
output:
main: thread END
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-0: System.getenv(HOME)=/Users/whan
vert.x-eventloop-thread-0: System.getProperty(prop)=Mac OS X
vert.x-eventloop-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
