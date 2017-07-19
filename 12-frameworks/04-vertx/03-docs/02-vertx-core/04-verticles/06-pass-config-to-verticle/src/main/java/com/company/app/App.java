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

    System.out.println(Thread.currentThread().getName() + ": config() name=" + config().getString("name"));

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

    JsonObject config = new JsonObject()
        .put("name", "tim")
        .put("directory", "/blah");

    System.out.println("config: " + config);

    Vertx vertx = Vertx.vertx();

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    // NOTE: Deploying verticles programmatically
    vertx.deployVerticle(new StandardVerticle(), options, ar -> {

      if (ar.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");

        vertx.undeploy(ar.result(), undeploy -> {
          System.out.println(Thread.currentThread().getName() + ": vertx.undeploy() handler");
          // self terminated
          System.exit(0);
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
config: {"name":"tim","directory":"/blah"}
main: thread END
vert.x-eventloop-thread-0: StandardVerticle.start() is called
vert.x-eventloop-thread-0: config() name=tim
vert.x-eventloop-thread-0: vertx.runOnContext() handler end
vert.x-eventloop-thread-1: vertx.deployVerticle() handler
Deployment_id=6b1a9c93-4d38-4683-b395-35555826b5b7
vert.x-eventloop-thread-0: StandardVerticle.stop() is called
vert.x-eventloop-thread-1: vertx.undeploy() handler
 */
