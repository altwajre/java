package com.company.app.integration;

import org.junit.Test;

public class UsePortVariableInOtherPluginIT {

  @Test
  public void testIntegration() {
    Integer port = Integer.getInteger("http.port", 8080);
    System.out.println("integration: port="+port);

    String portProperty = System.getProperty("http.port");
    System.out.println("System.getProperty('http.port')=" + portProperty);
  }

}
