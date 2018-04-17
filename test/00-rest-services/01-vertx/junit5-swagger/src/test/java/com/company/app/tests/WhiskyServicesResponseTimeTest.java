package com.company.app.tests;

import com.company.app.common.GlobalMapper;
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

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

//https://www.joecolantonio.com/2015/12/28/rest-assured-how-to-check-response-times/
public class WhiskyServicesResponseTimeTest {
  private ObjectMapper mapper = GlobalMapper.INSTANCE.mapper();

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
  public void printCreateResponseTime() {
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

  @Test
  public void verifyGetResponseTime() {
    Response response = get("/api/whiskies/0");
    response.then().time(lessThan(10L));
    JsonNode whisky = response
        .as(JsonNode.class);
    System.out.println(whisky);
  }
/*
output:
java.lang.AssertionError: 1 expectation failed.
Expected response time was not a value less than <10L> milliseconds, was 739 milliseconds (739 milliseconds).
 */

}
