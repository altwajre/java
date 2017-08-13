package com.company.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RunWith(VertxUnitRunner.class)
public class CustomerVerticleTest {

  private Vertx vertx;
  private Integer hostPort = 8081;
  private DockerClient docker;
  private String containerId;
  private String imageId;

  @Before
  public void setUp(TestContext context) throws Exception {

    vertx = Vertx.vertx();

    String imageName = "local-customer";
    String dockerDirectory = System.getProperty("user.dir") + "/../customer";

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

    System.out.println("# Finished setup");
  }

  @After
  public void tearDown(TestContext context) throws Exception {
    vertx.close(context.asyncAssertSuccess());

    docker.stopContainer(containerId, 10);
//    docker.listImages().forEach(i -> {
//      System.out.println(i.id());
//      try {
//        docker.removeImage(i.id(), true, true);
//      } catch (DockerException e) {
//        e.printStackTrace();
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    });
//    System.out.println("#imageId: "+imageId);
    docker.removeImage(imageId, true, true);
  }

  @Test
  public void testGetAll(TestContext context) {
    System.out.println("#testGetAll");
    Async async = context.async();

    vertx.createHttpClient().getNow(hostPort, "localhost", "/api/customers", response -> {
      response.handler(body -> {
        System.out.println(body.toString());
        context.assertTrue(body.toString().contains("Tom"));
        async.complete();
      });
    });
  }

}
