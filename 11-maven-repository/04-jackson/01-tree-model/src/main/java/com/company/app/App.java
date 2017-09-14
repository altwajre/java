package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

/*
https://www.mkyong.com/java/jackson-tree-model-example/
 */
public class App {
  public static void main(String[] args) throws IOException {

    traversingOneUser();

    traversingUsers();

    crud();

  }

  private static void crud() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(new File("src/main/resources/data/user.json"));

    final String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    System.out.println("Before Update: \n" + resultOriginal);

    // 1. Update id to 1000
    ((ObjectNode) root).put("id", 1000L);

    // 2. If middle name is empty, update to M
    JsonNode nameNode = root.path("name");
    if ("".equals(nameNode.path("middle").asText())) {
      ((ObjectNode) nameNode).put("middle", "M");
    }

    // 3. Create a new field nickName in nameNode
    ((ObjectNode) nameNode).put("nickname", "Will");

    // 4. Remove last field in nameNode
    ((ObjectNode) nameNode).remove("last");

    // 5. Create a new ObjectNode and add to root
    ObjectNode positionNode = mapper.createObjectNode();
    positionNode.put("name", "Developer");
    positionNode.put("years", 10);
    ((ObjectNode) root).set("position", positionNode);

    final String resultUpdated = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    System.out.println("After Update: \n" + resultUpdated);
  }
/*
Before Update:
{
  "id" : 1,
  "name" : {
    "first" : "Yong",
    "last" : "Tom"
  },
  "contact" : [ {
    "type" : "phone/home",
    "ref" : "111-111-1234"
  }, {
    "type" : "phone/work",
    "ref" : "222-222-2222"
  } ]
}
After Update:
{
  "id" : 1000,
  "name" : {
    "first" : "Yong",
    "middle" : "M",
    "nickname" : "Will"
  },
  "contact" : [ {
    "type" : "phone/home",
    "ref" : "111-111-1234"
  }, {
    "type" : "phone/work",
    "ref" : "222-222-2222"
  } ],
  "position" : {
    "name" : "Developer",
    "years" : 10
  }
}
 */

  private static void traversingUsers() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode rootArray = mapper.readTree(new File("src/main/resources/data/users.json"));

    for (JsonNode root : rootArray) {
      long id = root.path("id").asLong();
      System.out.println("id: " + id);
      final JsonNode nameNode = root.path("name");
      final String firstName = nameNode.path("first").asText();
      // missing node will return empty string
      final String middleName = nameNode.path("middle").asText();
      final String lastName = nameNode.path("last").asText();
      System.out.println("firstName: " + firstName);
      System.out.println("middleName: " + middleName);
      System.out.println("lastName: " + lastName);

      // Get Contact
      final JsonNode contactNode = root.path("contact");
      for (JsonNode node : contactNode) {
        final String type = node.path("type").asText();
        final String ref = node.path("ref").asText();
        System.out.println("type: " + type);
        System.out.println("ref: " + ref);
      }
    }
  }
/*
id: 1
firstName: Yong
middleName:
lastName: Mook Kim
type: phone/home
ref: 111-111-1234
type: phone/work
ref: 222-222-2222
id: 2
firstName: Yong
middleName:
lastName: Zi Lap
type: phone/home
ref: 333-333-1234
type: phone/work
ref: 444-444-4444
 */

  private static void traversingOneUser() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(new File("src/main/resources/data/user.json"));

    long id = root.path("id").asLong();
    System.out.println("id: " + id);
    final JsonNode nameNode = root.path("name");
    final String firstName = nameNode.path("first").asText();
    // missing node will return empty string
    final String middleName = nameNode.path("middle").asText();
    final String lastName = nameNode.path("last").asText();
    System.out.println("firstName: " + firstName);
    System.out.println("middleName: " + middleName);
    System.out.println("lastName: " + lastName);

    // Get Contact
    final JsonNode contactNode = root.path("contact");
    for (JsonNode node : contactNode) {
      final String type = node.path("type").asText();
      final String ref = node.path("ref").asText();
      System.out.println("type: " + type);
      System.out.println("ref: " + ref);
    }
  }
/*
id: 1
firstName: Yong
middleName:
lastName: Tom
type: phone/home
ref: 111-111-1234
type: phone/work
ref: 222-222-2222
 */
}
