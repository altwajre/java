package com.company.app;

import com.company.app.contracts.Validator;
import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.assertj.core.api.Assertions.assertThat;

public class WhiskyValidator extends WhiskyValidatorBase implements Validator {

  private ObjectMapper mapper = new ObjectMapper();
  private JsonNode expected;

  public WhiskyValidator(WhiskyClient whiskyClient) {
    super(whiskyClient);

    expected = mapper.createObjectNode();
  }

  @Override
  public JsonNode get(String id) {
    JsonNode actual = super.get(id);
    validate(this.expected, actual);
    return actual;
  }

  @Override
  public JsonNode getAll() {
    JsonNode actual = super.getAll();
    return actual;
  }

  @Override
  public JsonNode create(JsonNode body) {
    JsonNode actual = super.create(body);
    captureCurrentState(body, actual);
    validate(this.expected, actual);
    return actual;
  }

  @Override
  public JsonNode update(String id, JsonNode body) {
    JsonNode actual = super.update(id, body);
    captureCurrentState(body, actual);
    validate(this.expected, actual);
    return actual;
  }

  @Override
  public String delete(String id) {
    String actual = super.delete(id);
    return actual;
  }

  @Override
  public void validate(JsonNode expected, JsonNode actual) {
    assertThat(actual.path("name").textValue())
        .isEqualToIgnoringCase(expected.path("name").textValue());
    assertThat(actual.path("origin").textValue())
        .isEqualToIgnoringCase(expected.path("origin").textValue());
  }

  private void captureCurrentState(JsonNode request, JsonNode response) {
    ((ObjectNode) expected).put("name", request.path("name").textValue());
    ((ObjectNode) expected).put("origin", request.path("origin").textValue());
    ((ObjectNode) expected).put("id", response.path("id").toString());
  }

}
