package com.company.app.tests;

import com.company.app.common.ResourceHelper;
import com.company.app.contracts.WhiskyClient;
import com.company.app.implementations.factories.WhiskyValidatorClientFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WhiskyServicesValidatorTest {

  private WhiskyClient whisky;

  @AfterAll
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
  }

  @AfterAll
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @BeforeEach
  public void setUp() {
    whisky = new WhiskyValidatorClientFactory().create();
  }

  // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
  @Test
  public void createTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);
  }

  // curl -X PUT http://localhost:8080/api/whiskies/{id} -d '{"name": "Bowmore 18", "origin": "Scotland"}'
  @Test
  public void updateTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.update(id, ResourceHelper.get("data/whisky/update.json"));
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/activate -d {}
  @Test
  public void activateTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.activate(id, ResourceHelper.get("data/whisky/empty.json"));
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/suspend -d {}
  @Test
  public void suspendTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.suspend(id, ResourceHelper.get("data/whisky/empty.json"));
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/unsuspend -d {}
  @Test
  public void unsuspendTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.suspend(id, ResourceHelper.get("data/whisky/empty.json"));
    whisky.unsuspend(id, ResourceHelper.get("data/whisky/empty.json"));
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/cancel -d {}
  @Test
  public void cancelTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.cancel(id, ResourceHelper.get("data/whisky/empty.json"));
  }

  // curl -X DELETE http://localhost:8080/api/whiskies/1
  @Test
  public void deleteTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    whisky.delete(id);
  }

  // curl http://localhost:8080/api/whiskies/1
  @Test
  public void getOneTest() throws IOException {
    JsonNode createResp = whisky.create(ResourceHelper.get("data/whisky/create.json"));

    String id = Integer.toString(createResp.path("id").intValue());
    JsonNode getResp = whisky.get(id);
    System.out.println(getResp.toString());
  }

  // curl http://localhost:8080/api/whiskies
  @Test
  public void getAllTest() throws IOException {
    whisky.create(ResourceHelper.get("data/whisky/create.json"));

    JsonNode getAllResp = whisky.getAll();
    System.out.println(getAllResp.toString());
  }

}
