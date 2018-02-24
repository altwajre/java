package com.company.app.contracts;

import io.vertx.core.json.JsonObject;

public interface Validator {
  void validate(JsonObject expected, JsonObject actual);
}
