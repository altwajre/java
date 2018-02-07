package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredClient {
  public static JsonNode get(String path) {
    Response response = given()
        .contentType(ContentType.JSON)
        .get(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonNode.class);
  }
  public static JsonNode post(String path, JsonNode requestBody) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .post(path);
    response
        .then()
        .assertThat()
        .statusCode(201);

    return response.as(JsonNode.class);
  }
  public static JsonNode put(String path, JsonNode requestBody) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .put(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonNode.class);
  }
  public static String delete(String path) {
    Response response = given()
        .contentType(ContentType.JSON)
        .delete(path);
    response
        .then()
        .assertThat()
        .statusCode(204);

    return response.thenReturn().asString();
  }
}
