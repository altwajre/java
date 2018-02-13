package com.company.app.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.jayway.restassured.RestAssured.given;

public class WhiskyServicesResponseTimeTest {
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
  public void createResponseTimeTest() {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Nikka");
    ((ObjectNode) whisky).put("origin", "Japanese");

    Response response = given()
        .contentType(ContentType.JSON)
        .body(whisky)
        .when()
        .post("/api/whiskies");
    long timeInMilliSeconds = response
        .timeIn(TimeUnit.MILLISECONDS);
    System.out.println(timeInMilliSeconds);

    JsonNode createResponse = response.as(JsonNode.class);
    System.out.println(createResponse);
  }
}
