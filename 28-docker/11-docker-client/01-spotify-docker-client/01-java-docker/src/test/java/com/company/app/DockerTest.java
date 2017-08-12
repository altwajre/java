package com.company.app;

import com.google.common.io.Resources;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

public class DockerTest {
  @Before
  public void setUp() throws Exception {
    final DockerClient docker = DefaultDockerClient.fromEnv().build();

    final String dockerDirectory = Resources.getResource("dockerDirectory").getPath();
    docker.build(Paths.get(dockerDirectory), "local-customer");
  }

  @Test
  public void testDockerImage() {
    System.out.println("#testDockerImage");
  }
}

