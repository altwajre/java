package com.company.app.implementations.clients;

import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.JsonNode;

// Decorator
public abstract class WhiskyValidatorBase implements WhiskyClient {

  private WhiskyClient whiskyClient;

  public WhiskyValidatorBase(WhiskyClient whiskyClient) {
    this.whiskyClient = whiskyClient;
  }

  @Override
  public JsonNode get(String id) {
    return whiskyClient.get(id);
  }

  @Override
  public JsonNode getAll() {
    return whiskyClient.getAll();
  }

  @Override
  public JsonNode create(JsonNode body) {
    return whiskyClient.create(body);
  }

  @Override
  public JsonNode update(String id, JsonNode body) {
    return whiskyClient.update(id, body);
  }

  @Override
  public JsonNode activate(String id, JsonNode requestBody) {
    return whiskyClient.activate(id, requestBody);
  }

  @Override
  public JsonNode suspend(String id, JsonNode requestBody) {
    return whiskyClient.suspend(id, requestBody);
  }

  @Override
  public JsonNode unsuspend(String id, JsonNode requestBody) {
    return whiskyClient.unsuspend(id, requestBody);
  }

  @Override
  public JsonNode cancel(String id, JsonNode requestBody) {
    return whiskyClient.cancel(id, requestBody);
  }

  @Override
  public String delete(String id) {
    return whiskyClient.delete(id);
  }
}
