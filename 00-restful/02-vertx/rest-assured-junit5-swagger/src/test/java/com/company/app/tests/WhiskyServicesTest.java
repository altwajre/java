package com.company.app.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WhiskyServicesTest {

  @BeforeAll
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
  }

  @AfterAll
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
  @Test
  public void createTest() {
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
  }

  // curl -X PUT http://localhost:8080/api/whiskies/1 -d '{"name": "Bowmore 18", "origin": "Scotland"}'
  @Test
  public void updateTest() {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode createWhisky = mapper.createObjectNode();
    ((ObjectNode) createWhisky).put("name", "Nikka");
    ((ObjectNode) createWhisky).put("origin", "Japanese");

    JsonNode createResponse = given()
        .contentType(ContentType.JSON)
        .body(createWhisky)
        .when()
        .post("/api/whiskies")
        .as(JsonNode.class);
    System.out.println(createResponse);
    String id = createResponse.get("id").toString();

    JsonNode updateWhisky = mapper.createObjectNode();
    ((ObjectNode) updateWhisky).put("name", "Redbreast");
    ((ObjectNode) updateWhisky).put("origin", "Irish");

    JsonNode updateResponse = given()
        .contentType(ContentType.JSON)
        .body(updateWhisky)
        .when()
        .put("/api/whiskies/" + id)
        .as(JsonNode.class);
    System.out.println(updateResponse);
  }

  // curl -X DELETE http://localhost:8080/api/whiskies/1
  @Test
  public void deleteTest() {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode createWhisky = mapper.createObjectNode();
    ((ObjectNode) createWhisky).put("name", "Nikka");
    ((ObjectNode) createWhisky).put("origin", "Japanese");

    JsonNode createResponse = given()
        .contentType(ContentType.JSON)
        .body(createWhisky)
        .when()
        .post("/api/whiskies")
        .as(JsonNode.class);
    System.out.println(createResponse);
    String id = createResponse.get("id").toString();

    String deleteResponse = delete("/api/whiskies/" + id)
        .thenReturn()
        .asString();
    System.out.println(deleteResponse);
    assertThat(deleteResponse).isEmpty();
  }

  // curl http://localhost:8080/api/whiskies/1
  @Test
  public void getOneTest() {
//    Response response = get("/api/whiskies/0");
//    ResponseBody body = response.getBody();
//    System.out.println(body.asString());

    JsonNode whisky = get("/api/whiskies/0")
        .as(JsonNode.class);
    System.out.println(whisky);
  }

  // curl http://localhost:8080/api/whiskies
  @Test
  public void getAllTest() {
    JsonNode whiskies = get("/api/whiskies")
        .as(JsonNode.class);

    System.out.println(whiskies);
  }

}
