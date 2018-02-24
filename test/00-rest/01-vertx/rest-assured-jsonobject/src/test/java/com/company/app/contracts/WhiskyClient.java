package com.company.app.contracts;

import io.vertx.core.json.JsonObject;

public interface WhiskyClient {
  JsonObject get(String id);
  JsonObject getAll();
  JsonObject create(JsonObject requestBody);
  JsonObject update(String id, JsonObject requestBody);
  JsonObject activate(String id, JsonObject requestBody);
  JsonObject suspend(String id, JsonObject requestBody);
  JsonObject unsuspend(String id, JsonObject requestBody);
  JsonObject cancel(String id, JsonObject requestBody);
  String delete(String id);
}
