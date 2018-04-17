package com.company.app.contracts;

import com.fasterxml.jackson.databind.JsonNode;

public interface Validator {
  void validate(JsonNode expected, JsonNode actual);
}
