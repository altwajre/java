package com.company.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.*;

import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {

    final DockerClient docker = DefaultDockerClient.fromEnv().build();

    String containerPort = "5000/tcp";
    String hostPort = "80";
    Map<String, List<PortBinding>> portBindings = Maps.newHashMap();
    // you can leave the host IP empty for the PortBinding.of first parameter
    portBindings.put(containerPort, Lists.newArrayList(PortBinding.of("0.0.0.0", hostPort)));

    final HostConfig hostConfig = HostConfig.builder().portBindings(portBindings).build();

    // Create container with exposed ports
    final ContainerConfig containerConfig = ContainerConfig.builder()
        .hostConfig(hostConfig)
        .image("myservice")
        .exposedPorts(containerPort)
        .build();

    final ContainerCreation creation = docker.createContainer(containerConfig);
    final String id = creation.id();

    // Inspect container
    final ContainerInfo info = docker.inspectContainer(id);

    // Start container
    docker.startContainer(id);

  }

}
/*
21:39:45.678 [main] DEBUG com.spotify.docker.client.DockerCertificates - /Users/whan/.docker/ca.pem, /Users/whan/.docker/key.pem or /Users/whan/.docker/cert.pem does not exist, not using SSL
21:39:45.872 [main] DEBUG com.spotify.docker.client.DockerConfigReader - Using configfile: /Users/whan/.docker/config.json
21:39:46.540 [main] INFO com.spotify.docker.client.DefaultDockerClient - Creating container with ContainerConfig: ContainerConfig{hostname=null, domainname=null, user=null, attachStdin=null, attachStdout=null, attachStderr=null, portSpecs=null, exposedPorts=[5000/tcp], tty=null, openStdin=null, stdinOnce=null, env=null, cmd=null, image=myservice, volumes={}, workingDir=null, entrypoint=null, networkDisabled=null, onBuild=null, labels=null, macAddress=null, hostConfig=HostConfig{binds=null, blkioWeight=null, blkioWeightDevice=null, blkioDeviceReadBps=null, blkioDeviceWriteBps=null, blkioDeviceReadIOps=null, blkioDeviceWriteIOps=null, containerIdFile=null, lxcConf=null, privileged=null, portBindings={5000/tcp=[PortBinding{hostIp=0.0.0.0, hostPort=80}]}, links=null, publishAllPorts=null, dns=null, dnsOptions=null, dnsSearch=null, extraHosts=null, volumesFrom=null, capAdd=null, capDrop=null, networkMode=null, securityOpt=null, devices=null, memory=null, memorySwap=null, memorySwappiness=null, memoryReservation=null, nanoCpus=null, cpuPeriod=null, cpuShares=null, cpusetCpus=null, cpusetMems=null, cpuQuota=null, cgroupParent=null, restartPolicy=null, logConfig=null, ipcMode=null, ulimits=null, pidMode=null, shmSize=null, oomKillDisable=null, oomScoreAdj=null, autoRemove=null, pidsLimit=null, tmpfs=null, readonlyRootfs=null, storageOpt=null}, stopSignal=null, healthcheck=null, networkingConfig=null}
21:39:47.477 [main] INFO com.spotify.docker.client.DefaultDockerClient - Starting container with Id: 41b65253f1a89a091b9ab7191ffdd75a837922c11edc5157cb5854e9325fdc36
 */
