package com.company.app.tests;

import com.company.app.common.ResourceHelper;
import com.company.app.contracts.WhiskyClient;
import com.company.app.implementations.factories.WhiskyValidatorFactoryImpl;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class WhiskyServicesValidatorTest {

  private WhiskyClient whisky;
  private String baseURI = "http://localhost";
  private String port = ":8080";

  @BeforeEach
  public void setUp() {
    whisky = new WhiskyValidatorFactoryImpl().create();
  }

  @Test
  public void createSpring() throws IOException {
    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    String path = "/api/whiskies";
    String url = baseURI + port + path;
    JsonNode createBody = ResourceHelper.get("data/whisky/create.json");

    JsonNode createResp = restTemplate.postForObject(url, new HttpEntity<>(createBody), JsonNode.class);

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

  }

  @Test
  public void updateSpring() throws IOException {
    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    String path = "/api/whiskies";
    String url = baseURI + port + path;
    JsonNode createBody = ResourceHelper.get("data/whisky/create.json");

    JsonNode createResp = restTemplate.postForObject(url, new HttpEntity<>(createBody), JsonNode.class);

    String id = Integer.toString(createResp.path("id").intValue());
    whisky.get(id);

    JsonNode updateBody = ResourceHelper.get("data/whisky/update.json");

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<JsonNode> requestUpdate = new HttpEntity<>(updateBody, httpHeaders);
    ResponseEntity<JsonNode> updateResp = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, requestUpdate, JsonNode.class);
    JsonNode updateRespBody = updateResp.getBody();
    String respJsonBody = updateRespBody.toString();
    System.out.println(respJsonBody);
  }

  // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
  @Test
  public void createTest() throws IOException {
    JsonNode requestBody = ResourceHelper.get("data/whisky/create.json");
    JsonNode createResp = whisky.create(requestBody);

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
