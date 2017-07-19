package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
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

public class App {
  public static void main(String[] args) {

    System.out.println("StandardVerticle.getCanonicalName()=" + StandardVerticle.class.getCanonicalName());

    Vertx vertx = Vertx.vertx();

    // NOTE: Deploying verticles programmatically
    vertx.deployVerticle(new StandardVerticle(),
        ar -> {  // Waiting for deployment to complete

          if (ar.succeeded()) {
            System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");
            System.out.println("Deployment_id=" + ar.result());

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
StandardVerticle.getCanonicalName()=com.company.app.StandardVerticle
main: thread END
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
Deployment_id=e2b738ca-8582-4c40-9a10-fae9780fba72
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
