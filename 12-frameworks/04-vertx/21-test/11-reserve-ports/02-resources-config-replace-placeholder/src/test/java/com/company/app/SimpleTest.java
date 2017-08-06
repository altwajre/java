package com.company.app;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class SimpleTest {
  @Test
  public void testSomething() throws IOException {

    ServerSocket socket = new ServerSocket(0);
    Integer port = socket.getLocalPort();
    System.out.println("reserve random port: " + port);
    socket.close();

  }
}
