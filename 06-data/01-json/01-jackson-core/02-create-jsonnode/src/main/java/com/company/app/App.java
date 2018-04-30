package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class App {
  static ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) throws IOException {
    createFromObjectNode();

    createFromString();

  }

  private static void createFromString() throws IOException {
    System.out.println("#createFromString");
    String jsonString = "{\"name\":\"Nikka\",\"origin\":\"Japan\"}";
    JsonNode jsonNode = mapper.readTree(jsonString);
    System.out.println(jsonNode);

    ((ObjectNode) jsonNode).put("age", 28);
    System.out.println("After added a field: " + jsonNode);
  }

  private static void createFromObjectNode() {
    System.out.println("#createFromObjectNode");
    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Nikka");
    ((ObjectNode) whisky).put("origin", "Japanese");
    ((ObjectNode) whisky).put("version", 8);

    String name = whisky.path("name").asText();
    System.out.println(name);
    int version = whisky.path("version").asInt();
    System.out.println(version);

    System.out.println(whisky.toString());
  }
}
/*
#createFromObjectNode
Nikka
8
{"name":"Nikka","origin":"Japanese","version":8}
#createFromString
{"name":"Nikka","origin":"Japan"}
After added a field: {"name":"Nikka","origin":"Japan","age":28}
 */
