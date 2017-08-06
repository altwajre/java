package com.company.app;

import java.io.IOException;
import java.net.ServerSocket;

/*
http://www.mojohaus.org/build-helper-maven-plugin/reserve-network-port-mojo.html

 */
public class App {
  public static void main(String[] args) throws IOException {

    for (int i = 0; i < 8; i++) {
      ServerSocket socket = new ServerSocket(0);
      Integer port = socket.getLocalPort();
      System.out.println("reserve random port: " + port);
    }

  }
}
/*
reserve random port: 55741
reserve random port: 55743
reserve random port: 55744
reserve random port: 55745
reserve random port: 55746
reserve random port: 55747
reserve random port: 55748
reserve random port: 55749
 */
