package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class App {
  public static void main(String[] args) {

    testRestAssured();

  }

  private static void testRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);

    getAllTest();
    String id = createTest();

    getAllTest();
    updateTest(id);

    getAllTest();
    deleteTest(id);

    getAllTest();
    getOneTest();
  }

  // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
  private static String createTest() {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Nikka");
    ((ObjectNode) whisky).put("origin", "Japanese");

    JsonNode response = given()
        .contentType(ContentType.JSON)
        .body(whisky)
        .when()
        .post("/api/whiskies")
        .as(JsonNode.class);
    System.out.println(response);
    return response.get("id").toString();
  }

  // curl -X PUT http://localhost:8080/api/whiskies/1 -d '{"name": "Bowmore 18", "origin": "Scotland"}'
  private static void updateTest(String id) {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Redbreast");
    ((ObjectNode) whisky).put("origin", "Irish");

    JsonNode response = given()
        .contentType(ContentType.JSON)
        .body(whisky)
        .when()
        .put("/api/whiskies/" + id)
        .as(JsonNode.class);
    System.out.println(response);
  }

  // curl -X DELETE http://localhost:8080/api/whiskies/1
  private static void deleteTest(String id) {
    String response = delete("/api/whiskies/" + id)
        .thenReturn()
        .asString();

    System.out.println(response);
  }

  // curl http://localhost:8080/api/whiskies/1
  private static void getOneTest() {
//    Response response = get("/api/whiskies/0");
//    ResponseBody body = response.getBody();
//    System.out.println(body.asString());

    JsonNode whisky = get("/api/whiskies/0")
        .as(JsonNode.class);
    System.out.println(whisky);
  }

  // curl http://localhost:8080/api/whiskies
  private static void getAllTest() {
    JsonNode whiskies = get("/api/whiskies")
        .as(JsonNode.class);

    System.out.println(whiskies);
  }
}
