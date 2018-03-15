package com.company.app.contracts;

import com.fasterxml.jackson.databind.JsonNode;

public interface WhiskyClient {
  JsonNode get(String id);
  JsonNode getAll();
  JsonNode create(JsonNode requestBody);
  JsonNode update(String id, JsonNode requestBody);
  JsonNode activate(String id, JsonNode requestBody);
  JsonNode suspend(String id, JsonNode requestBody);
  JsonNode unsuspend(String id, JsonNode requestBody);
  JsonNode cancel(String id, JsonNode requestBody);
  String delete(String id);
}
