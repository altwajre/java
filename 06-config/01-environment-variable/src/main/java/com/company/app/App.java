package com.company.app;

import java.util.Map;

public class App {
  public static void main(String[] args) {

    System.out.println("# Set env in terminal, and get env in code");
    // NOTE: set env var TEST_ENV in terminal `export TEST_ENV=dev`
    //       display env var `echo ${TEST_ENV}`

    String numberOfUsers = System.getenv("NumberOfUsers") == null ? "8" : System.getenv("NumberOfUsers");
    System.out.println("NumberOfUsers: " + numberOfUsers);

    System.out.println("TEST_ENV=" + System.getenv("TEST_ENV"));
    System.out.println("\n");

    System.out.println("# System.getenv()");
    Map<String, String> env = System.getenv();
    for (String envName : env.keySet()) {
      System.out.println(envName + ": " + env.get(envName));
    }
    System.out.println("\n");

    System.out.println("# Get PATH env var");
    // gets the value of the specified environment variable "PATH"
    System.out.println("PATH=" + System.getenv("PATH"));

  }
}
/*
Output:
# Set env in terminal, and get env in code
NumberOfUsers: 8
TEST_ENV=dev
...
 */
