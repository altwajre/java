package com.company.app.implementations;

import com.company.app.common.RestAssuredClient;
import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.JsonNode;

public class WhiskyClientImpl implements WhiskyClient {

  @Override
  public JsonNode get(String id) {
    return RestAssuredClient.get("/api/whiskies/" + id);
  }

  @Override
  public JsonNode getAll() {
    return get("");
  }

  @Override
  public JsonNode create(JsonNode requestBody) {
    return RestAssuredClient.post("/api/whiskies", requestBody);
  }

  @Override
  public JsonNode update(String id, JsonNode requestBody) {
    return RestAssuredClient.put("/api/whiskies/" + id, requestBody);
  }

  @Override
  public JsonNode activate(String id, JsonNode requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/activate", requestBody, 200);
  }

  @Override
  public JsonNode suspend(String id, JsonNode requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/suspend", requestBody, 200);
  }

  @Override
  public JsonNode unsuspend(String id, JsonNode requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/unsuspend", requestBody, 200);
  }

  @Override
  public JsonNode cancel(String id, JsonNode requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/cancel", requestBody, 200);
  }

  @Override
  public String delete(String id) {
    return RestAssuredClient.delete("/api/whiskies/" + id);
  }

}
