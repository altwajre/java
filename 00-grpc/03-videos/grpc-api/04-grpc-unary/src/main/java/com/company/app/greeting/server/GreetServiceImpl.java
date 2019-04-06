package com.company.app.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
  @Override
  public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
    Greeting greeting = request.getGreeting();
    String firstName = greeting.getFirstName();

    String result = "grpc Server: Hello " + firstName;
    GreetResponse response = GreetResponse.newBuilder()
        .setResult(result)
        .build();

    responseObserver.onNext(response);

    responseObserver.onCompleted();
  }
}
