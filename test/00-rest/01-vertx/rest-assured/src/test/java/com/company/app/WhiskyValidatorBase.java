package com.company.app;

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
  public String delete(String id) {
    return whiskyClient.delete(id);
  }
}
