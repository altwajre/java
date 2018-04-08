package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

public class AppTest {

  @Test
  public void foo() {
    JsonNode jsonNode = ResourceHelper.get("data/create.json");
    System.out.println(jsonNode.toString());

  }

}