package com.company.app.common;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import io.vertx.core.json.JsonObject;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredClient {
  public static JsonObject get(String path) {
    Response response = given()
        .contentType(ContentType.JSON)
        .get(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonObject.class);
  }

  public static JsonObject post(String path, JsonObject requestBody) {
    return post(path, requestBody, 201);
  }

  public static JsonObject post(String path, JsonObject requestBody, int expectedStatusCode) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .post(path);
    response
        .then()
        .assertThat()
        .statusCode(expectedStatusCode);

    return response.as(JsonObject.class);
  }

  public static JsonObject put(String path, JsonObject requestBody) {
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .put(path);
    response
        .then()
        .assertThat()
        .statusCode(200);

    return response.as(JsonObject.class);
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
