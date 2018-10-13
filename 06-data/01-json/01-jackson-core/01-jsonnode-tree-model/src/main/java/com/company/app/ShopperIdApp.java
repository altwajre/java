package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShopperIdApp {
  private static ObjectMapper mapper = new ObjectMapper();
  public static void main(String[] args) throws IOException {
    JsonNode jsonNode = mapper.readTree(new File("src/main/resources/data/shopperIds.json"));
    List list = JsonHelper.toPojo(jsonNode, List.class);
    System.out.println(list.get(0));
    System.out.println(list);
  }
}
/*
1680252
[1680252, 1680253, 1680254]
 */
