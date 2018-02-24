package com.company.app.implementations;

import com.company.app.contracts.WhiskyClient;
import io.vertx.core.json.JsonObject;

// Decorator
public abstract class WhiskyValidatorBase implements WhiskyClient {

  private WhiskyClient whiskyClient;

  public WhiskyValidatorBase(WhiskyClient whiskyClient) {
    this.whiskyClient = whiskyClient;
  }

  @Override
  public JsonObject get(String id) {
    return whiskyClient.get(id);
  }

  @Override
  public JsonObject getAll() {
    return whiskyClient.getAll();
  }

  @Override
  public JsonObject create(JsonObject body) {
    return whiskyClient.create(body);
  }

  @Override
  public JsonObject update(String id, JsonObject body) {
    return whiskyClient.update(id, body);
  }

  @Override
  public JsonObject activate(String id, JsonObject requestBody) {
    return whiskyClient.activate(id, requestBody);
  }

  @Override
  public JsonObject suspend(String id, JsonObject requestBody) {
    return whiskyClient.suspend(id, requestBody);
  }

  @Override
  public JsonObject unsuspend(String id, JsonObject requestBody) {
    return whiskyClient.unsuspend(id, requestBody);
  }

  @Override
  public JsonObject cancel(String id, JsonObject requestBody) {
    return whiskyClient.cancel(id, requestBody);
  }

  @Override
  public String delete(String id) {
    return whiskyClient.delete(id);
  }
}
