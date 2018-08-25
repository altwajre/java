package com.company.app.contracts;

import com.fasterxml.jackson.databind.JsonNode;

public interface RestClient {
  JsonNode get(String url);
  JsonNode post(String url, JsonNode requestBody);
  JsonNode put(String url, JsonNode requestBody);
  String delete(String url);
}
