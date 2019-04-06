package com.company.app.greeting.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {
  public static void main(String[] args) {
    System.out.println("grpc Client");
    ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50051)
        .usePlaintext()
        .build();

    GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

    Greeting greeting = Greeting.newBuilder()
        .setFirstName("Jake")
        .setLastName("Swenson")
        .build();

    GreetRequest greetRequest = GreetRequest.newBuilder()
        .setGreeting(greeting)
        .build();

    GreetResponse greetResponse = greetClient.greet(greetRequest);
    System.out.println(greetResponse.getResult());

    System.out.println("Client does something");

    channel.shutdown();
  }

}
