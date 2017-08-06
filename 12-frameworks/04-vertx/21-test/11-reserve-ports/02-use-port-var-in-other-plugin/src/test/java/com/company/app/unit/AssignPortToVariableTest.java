package com.company.app.unit;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class AssignPortToVariableTest {

  @Test
  public void foo() throws IOException {
    ServerSocket socket = new ServerSocket(0);
    Integer port = socket.getLocalPort();
    System.out.println("unit: port="+port);
    socket.close();
  }
}
