package com.company.app.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class RestAssuredClient {

  private RequestSpecification spec;

  public RestAssuredClient() {
    RestAssured.reset();
    spec = RestAssured.given()
        .baseUri("http://localhost")
        .port(8080);
  }

  public JsonNode get(String path) {
    Response response = spec
        .contentType(ContentType.JSON)
        .get(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonNode.class);
  }

  public JsonNode post(String path, JsonNode requestBody) {
    return post(path, requestBody, 201);
  }

  public JsonNode post(String path, JsonNode requestBody, int expectedStatusCode) {
    Response response = spec
        .contentType(ContentType.JSON)
        .body(requestBody)
        .post(path);
    response
        .then()
        .assertThat()
        .statusCode(expectedStatusCode);

    return response.as(JsonNode.class);
  }

  public JsonNode put(String path, JsonNode requestBody) {
    Response response = spec
        .contentType(ContentType.JSON)
        .body(requestBody)
        .put(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonNode.class);
  }

  public String delete(String path) {
    Response response = spec
        .contentType(ContentType.JSON)
        .delete(path);
    response
        .then()
        .assertThat()
        .statusCode(204);

    return response.thenReturn().asString();
  }

}
