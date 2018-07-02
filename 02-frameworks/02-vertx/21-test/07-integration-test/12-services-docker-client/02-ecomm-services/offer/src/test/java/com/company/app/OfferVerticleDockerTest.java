package com.company.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;
import com.spotify.docker.client.messages.PortBinding;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RunWith(VertxUnitRunner.class)
public class OfferVerticleDockerTest {

  private Vertx vertx;
  private Integer hostPort = 8081;
  private Integer port = 8082;
  private DockerClient docker;
  private String containerId;
  private String imageId;

  @Before
  public void setUp(TestContext context) throws Exception {

    vertx = Vertx.vertx();

    String imageName = "local-customer";
    String dockerDirectory = System.getProperty("user.dir") + "/../product";

    System.out.println(Paths.get(dockerDirectory));

    // image
    docker = DefaultDockerClient.fromEnv().build();

    imageId = docker.build(Paths.get(dockerDirectory), imageName);

    // container
    String containerPort = "8080/tcp";
    Map<String, List<PortBinding>> portBindings = Maps.newHashMap();
    // you can leave the host IP empty for the PortBinding.of first parameter
    portBindings.put(containerPort, Lists.newArrayList(PortBinding.of("0.0.0.0", hostPort)));

    final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

    // Create container with exposed ports
    final ContainerConfig containerConfig = ContainerConfig.builder()
        .hostConfig(hostConfig)
        .image(imageName)
        .exposedPorts(containerPort)
        .build();

    final ContainerCreation container = docker.createContainer(containerConfig);
    containerId = container.id();

    // Inspect container
    docker.inspectContainer(containerId);

    // Start container
    docker.startContainer(containerId);

    // MUST wait for container to be ready
    try {
      Thread.sleep(2800);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("# Finished setup product container");

    // Launch offer service locally
    JsonObject config = new JsonObject()
        .put("http.port", port);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    vertx.deployVerticle(new OfferVerticle(), options, context.asyncAssertSuccess());

  }

  @After
  public void tearDown(TestContext context) throws Exception {
    vertx.close(context.asyncAssertSuccess());

    docker.stopContainer(containerId, 10);
    docker.removeImage(imageId, true, true);
  }

  @Test
  public void testGetOne(TestContext context) {
    Async async = context.async();

    WebClient.create(vertx)
        .get(port, "localhost", "/api/offers/1")
        .as(BodyCodec.json(Offer.class))
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Offer> response = ar.result();

            Offer body = response.body();
            System.out.println("Body:\n" + body.toString());

          } else {
            System.out.println("Error=" + ar.cause());
          }
          async.complete();
        });

  }

}
