package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.*;

public class App {
  static ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) throws IOException {
    createFromObjectNode();

    createFromString();

    createArrayFromList();

    createNodeFromMap();
  }

  private static void createNodeFromMap() {
    System.out.println("# createNodeFromMap");
    Map<String, Object> map = new HashMap<>();
    map.put("name", "Tom");
    map.put("age", 28);
    map.put("likeCake", true);

    JsonNode properties = mapper.convertValue(map, JsonNode.class);
    System.out.println(properties);

    properties.forEach(d -> {
      System.out.println(d);
    });

    Map<String, Object> newMap = new HashMap<>();
    newMap.put("name", "Dick");
    newMap.put("age", 18);

    JsonNode newProperties = mapper.convertValue(newMap, JsonNode.class);
    Iterator<String> fieldNames = newProperties.fieldNames();
    while (fieldNames.hasNext()) {
      String fieldName = fieldNames.next();
//      System.out.println(fieldName);
      ((ObjectNode)properties).replace(fieldName, newProperties.path(fieldName));
    }
    System.out.println(properties);
  }

  private static void createFromString() throws IOException {
    System.out.println("#createFromString");
    String jsonString = "{\"name\":\"Nikka\",\"origin\":\"Japan\"}";
    JsonNode jsonNode = mapper.readTree(jsonString);
    System.out.println(jsonNode);

    ((ObjectNode) jsonNode).put("age", 28);
    System.out.println("After added a field: " + jsonNode);
  }
/*
#createFromString
{"name":"Nikka","origin":"Japan"}
After added a field: {"name":"Nikka","origin":"Japan","age":28}
 */

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
/*
#createFromObjectNode
Nikka
8
{"name":"Nikka","origin":"Japanese","version":8}
 */

  private static void createArrayFromList() {
    System.out.println("#createArrayFromList");

    List<Person> people = new ArrayList<Person>();
    people.add(new Person("Tom", 18));
    people.add(new Person("Harry", 28));
    ArrayNode peopleNode = mapper.valueToTree(people);

    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Nikka");
    ((ObjectNode) whisky).put("origin", "Japanese");
    ((ObjectNode) whisky).put("version", 8);
    ((ObjectNode) whisky).putArray("people").addAll(peopleNode);

    JsonNode will = mapper.valueToTree(new Person("Will", 38));
    // NOTE: use withArray to append
    ((ObjectNode) whisky).withArray("people").add(will);

    String name = whisky.path("name").asText();
    System.out.println(name);
    int version = whisky.path("version").asInt();
    System.out.println(version);
    JsonNode peopleArray = whisky.path("people");
    for(JsonNode node : peopleArray) {
      String personName = node.path("name").asText();
      System.out.println(personName);
      int age = node.path("age").asInt();
      System.out.println(age);
    }

    System.out.println(whisky.toString());
  }
/*
#createFromObjectNode
Nikka
8
{"name":"Nikka","origin":"Japanese","version":8}
#createFromString
{"name":"Nikka","origin":"Japan"}
After added a field: {"name":"Nikka","origin":"Japan","age":28}
#createArrayFromList
Nikka
8
Tom
18
Harry
28
Will
38
{"name":"Nikka","origin":"Japanese","version":8,"people":[{"name":"Tom","age":18},{"name":"Harry","age":28},{"name":"Will","age":38}]}
# createNodeFromMap
{"name":"Tom","likeCake":true,"age":28}
"Tom"
true
28
{"name":"Dick","likeCake":true,"age":18}
 */

}
