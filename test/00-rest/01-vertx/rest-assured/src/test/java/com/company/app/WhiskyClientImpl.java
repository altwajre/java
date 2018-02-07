package com.company.app;

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
  public String delete(String id) {
    return RestAssuredClient.delete("/api/whiskies/" + id);
  }

}
