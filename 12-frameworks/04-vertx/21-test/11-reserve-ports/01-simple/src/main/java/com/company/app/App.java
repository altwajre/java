package com.company.app;

import java.io.IOException;
import java.net.ServerSocket;

/*
http://www.mojohaus.org/build-helper-maven-plugin/reserve-network-port-mojo.html

 */
public class App {
  public static void main(String[] args) throws IOException {

    for (int i = 0; i < 3; i++) {
      ServerSocket socket = new ServerSocket(0);
      Integer port = socket.getLocalPort();
      System.out.println("reserve random port: " + port);
      socket.close();
    }

  }
}
/*
reserve random port: 55741
reserve random port: 55743
reserve random port: 55744
 */
