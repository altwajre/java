package com.company.app;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerCertificateException;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws DockerCertificateException, DockerException, InterruptedException {
    final DockerClient docker = DefaultDockerClient.fromEnv().build();

    // Bind container ports to host ports
    final String[] ports = {"80", "22"};
    final Map<String, List<PortBinding>> portBindings = new HashMap<>();
    for (String port : ports) {
      List<PortBinding> hostPorts = new ArrayList<>();
      hostPorts.add(PortBinding.of("0.0.0.0", port));
      portBindings.put(port, hostPorts);
    }

// Bind container port 443 to an automatically allocated available host port.
    List<PortBinding> randomPort = new ArrayList<>();
    randomPort.add(PortBinding.randomPort("0.0.0.0"));
    portBindings.put("443", randomPort);

    final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

// Create container with exposed ports
    final ContainerConfig containerConfig = ContainerConfig.builder()
        .hostConfig(hostConfig)
        .image("myservice")
        .build();

    final ContainerCreation creation = docker.createContainer(containerConfig);
    final String id = creation.id();

// Inspect container
    final ContainerInfo info = docker.inspectContainer(id);

// Start container
    docker.startContainer(id);
  }
}
