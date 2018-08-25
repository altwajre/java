package com.company.app.implementations;

import com.company.app.common.RestAssuredClient;
import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.JsonNode;

public class WhiskyRestAssuredClient implements WhiskyClient {

  RestAssuredClient client;

  public WhiskyRestAssuredClient() {
    client = new RestAssuredClient();
  }

  @Override
  public JsonNode get(String id) {
    return client.get("/api/whiskies/" + id);
  }

  @Override
  public JsonNode getAll() {
    return get("");
  }

  @Override
  public JsonNode create(JsonNode requestBody) {
    return client.post("/api/whiskies", requestBody);
  }

  @Override
  public JsonNode update(String id, JsonNode requestBody) {
    return client.put("/api/whiskies/" + id, requestBody);
  }

  @Override
  public JsonNode activate(String id, JsonNode requestBody) {
    return client.post("/api/whiskies/" + id + "/activate", requestBody, 200);
  }

  @Override
  public JsonNode suspend(String id, JsonNode requestBody) {
    return client.post("/api/whiskies/" + id + "/suspend", requestBody, 200);
  }

  @Override
  public JsonNode unsuspend(String id, JsonNode requestBody) {
    return client.post("/api/whiskies/" + id + "/unsuspend", requestBody, 200);
  }

  @Override
  public JsonNode cancel(String id, JsonNode requestBody) {
    return client.post("/api/whiskies/" + id + "/cancel", requestBody, 200);
  }

  @Override
  public String delete(String id) {
    return client.delete("/api/whiskies/" + id);
  }

}
