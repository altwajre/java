package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;

public class App {
  public static void main(String[] args) {
    JsonNode jsonNode = ResourceHelper.get("data/create.json");
    System.out.println(jsonNode.toString());
//    callScalaMethods();
  }

//  private static void callScalaMethods() {
//    new Companion().hello(); // instance method
//    Companion.hallo(); // static method
//  }
}
/*
output:
Hello (class)
Hello (object)
 */
