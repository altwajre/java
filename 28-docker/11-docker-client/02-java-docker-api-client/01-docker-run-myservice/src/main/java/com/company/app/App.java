package com.company.app;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

public class App
{
    public static void main( String[] args )
    {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost("tcp://localhost:2375")
            .build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
            .build();

//        dockerClient.createContainerCmd("")

    }
}
