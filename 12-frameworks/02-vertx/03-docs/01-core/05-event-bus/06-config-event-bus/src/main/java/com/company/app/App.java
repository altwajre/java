package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.http.ClientAuth;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

/*
Configuring the event bus

The event bus can be configured. It is particularly useful when the event bus is clustered. Under the hood the event bus
uses TCP connections to send and receive message, so the EventBusOptions let you configure all aspects of these TCP
connections. As the event bus acts as a server and client, the configuration is close to NetClientOptions and NetServerOptions.
 */
public class App {
  public static void main(String[] args) {

    VertxOptions options = new VertxOptions();

    // set HazelcastClusterManager to be ClusterManager classpath
    options.setClusterManager(new HazelcastClusterManager());

    // NOTE: Configuring the event bus
    options.setEventBusOptions(new EventBusOptions()
        .setClientAuth(ClientAuth.REQUIRED));

    Vertx.clusteredVertx(options, ar -> {
      if (ar.succeeded()) {
        Vertx vertx = ar.result();
        EventBus eventBus = vertx.eventBus();
        System.out.println("Clustered event bus: " + eventBus);
      } else {
        System.out.println("Failed: " + ar.cause());
      }
    });
  }
}
/*
output:
Jul 21, 2017 9:35:08 PM com.hazelcast.config.AbstractXmlConfigHelper
WARNING: Name of the hazelcast schema location incorrect using default
Jul 21, 2017 9:35:08 PM com.hazelcast.instance.DefaultAddressPicker
INFO: [LOCAL] [dev] [3.6.3] Prefer IPv4 stack is true.
Jul 21, 2017 9:35:08 PM com.hazelcast.instance.DefaultAddressPicker
INFO: [LOCAL] [dev] [3.6.3] Picked Address[192.168.1.6]:5701, using socket ServerSocket[addr=/0:0:0:0:0:0:0:0,localport=5701], bind any local is true
Jul 21, 2017 9:35:08 PM com.hazelcast.system
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Hazelcast 3.6.3 (20160527 - 08b28c3) starting at Address[192.168.1.6]:5701
Jul 21, 2017 9:35:08 PM com.hazelcast.system
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
Jul 21, 2017 9:35:08 PM com.hazelcast.system
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Configured Hazelcast Serialization version : 1
Jul 21, 2017 9:35:08 PM com.hazelcast.spi.OperationService
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Backpressure is disabled
Jul 21, 2017 9:35:08 PM com.hazelcast.spi.impl.operationexecutor.classic.ClassicOperationExecutor
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Starting with 4 generic operation threads and 8 partition operation threads.
Jul 21, 2017 9:35:08 PM com.hazelcast.instance.Node
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Creating MulticastJoiner
Jul 21, 2017 9:35:08 PM com.hazelcast.core.LifecycleService
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Address[192.168.1.6]:5701 is STARTING
Jul 21, 2017 9:35:08 PM com.hazelcast.nio.tcp.nonblocking.NonBlockingIOThreadingModel
INFO: [192.168.1.6]:5701 [dev] [3.6.3] TcpIpConnectionManager configured with Non Blocking IO-threading model: 3 input threads and 3 output threads
Jul 21, 2017 9:35:11 PM com.hazelcast.cluster.impl.MulticastJoiner
INFO: [192.168.1.6]:5701 [dev] [3.6.3]


Members [1] {
	Member [192.168.1.6]:5701 this
}

Jul 21, 2017 9:35:11 PM com.hazelcast.core.LifecycleService
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Address[192.168.1.6]:5701 is STARTED
Jul 21, 2017 9:35:11 PM com.hazelcast.partition.InternalPartitionService
INFO: [192.168.1.6]:5701 [dev] [3.6.3] Initializing cluster partition table arrangement...
Clustered event bus: io.vertx.core.eventbus.impl.clustered.ClusteredEventBus@2a3e7f56

Process finished with exit code 130 (interrupted by signal 2: SIGINT)
 */
