package com.company.app.implementations;

import com.company.app.common.RestAssuredClient;
import com.company.app.contracts.WhiskyClient;
import io.vertx.core.json.JsonObject;

public class WhiskyClientImpl implements WhiskyClient {

  @Override
  public JsonObject get(String id) {
    return RestAssuredClient.get("/api/whiskies/" + id);
  }

  @Override
  public JsonObject getAll() {
    return get("");
  }

  @Override
  public JsonObject create(JsonObject requestBody) {
    return RestAssuredClient.post("/api/whiskies", requestBody);
  }

  @Override
  public JsonObject update(String id, JsonObject requestBody) {
    return RestAssuredClient.put("/api/whiskies/" + id, requestBody);
  }

  @Override
  public JsonObject activate(String id, JsonObject requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/activate", requestBody, 200);
  }

  @Override
  public JsonObject suspend(String id, JsonObject requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/suspend", requestBody, 200);
  }

  @Override
  public JsonObject unsuspend(String id, JsonObject requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/unsuspend", requestBody, 200);
  }

  @Override
  public JsonObject cancel(String id, JsonObject requestBody) {
    return RestAssuredClient.post("/api/whiskies/" + id + "/cancel", requestBody, 200);
  }

  @Override
  public String delete(String id) {
    return RestAssuredClient.delete("/api/whiskies/" + id);
  }

}
