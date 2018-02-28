package com.company.app.implementations;

import com.company.app.common.CreateMethodNotCalledException;
import com.company.app.common.State;
import com.company.app.common.WhiskyPayload;
import com.company.app.contracts.Validator;
import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

// Decorator
public class WhiskyValidator extends WhiskyValidatorBase implements Validator {

  private ObjectMapper mapper = new ObjectMapper();
  private WhiskyPayload currentState;

  public WhiskyValidator(WhiskyClient whiskyClient) {
    super(whiskyClient);
  }

  @Override
  public JsonNode get(String id) {
    System.out.println("WhiskyValidator.get()");
    JsonNode actual = super.get(id);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode getAll() {
    JsonNode actual = super.getAll();
    return actual;
  }

  @Override
  public JsonNode create(JsonNode body) {
    currentState = new WhiskyPayload(); // create() is the starting point. Test should fail without create() first.

    System.out.println("WhiskyValidator.create()");
    JsonNode actual = super.create(body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode update(String id, JsonNode body) {
    System.out.println("WhiskyValidator.update()");

    if (isNull(currentState))
      throw new CreateMethodNotCalledException();

    JsonNode actual = super.update(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode activate(String id, JsonNode body) {
    System.out.println("WhiskyValidator.activate()");

    if (isNull(currentState))
      throw new CreateMethodNotCalledException();

    JsonNode actual = super.activate(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode suspend(String id, JsonNode body) {
    System.out.println("WhiskyValidator.suspend()");

    if (isNull(currentState))
      throw new CreateMethodNotCalledException();

    JsonNode actual = super.suspend(id, body);
    currentState.setState(State.SUSPENDED);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode unsuspend(String id, JsonNode body) {
    System.out.println("WhiskyValidator.unsuspend()");

    if (isNull(currentState))
      throw new CreateMethodNotCalledException();

    JsonNode actual = super.unsuspend(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public JsonNode cancel(String id, JsonNode body) {
    System.out.println("WhiskyValidator.cancel()");

    if (isNull(currentState))
      throw new CreateMethodNotCalledException();

    JsonNode actual = super.cancel(id, body);
    currentState.setState(State.CANCELLED);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonNode.class), actual);
    return actual;
  }

  @Override
  public String delete(String id) {
    System.out.println("WhiskyValidator.delete()");
    String actual = super.delete(id);
    return actual;
  }

  @Override
  public void validate(JsonNode expected, JsonNode actual) {
    String name = expected.path("name").textValue();
    if (name != null)
      assertThat(actual.path("name").textValue()).isEqualToIgnoringCase(name);

    String origin = expected.path("origin").textValue();
    if (origin != null)
      assertThat(actual.path("origin").textValue()).isEqualToIgnoringCase(origin);

    String state = expected.path("state").textValue();
    if (state != null)
      assertThat(actual.path("state").textValue()).isEqualToIgnoringCase(state);
  }

  private void captureCurrentState(JsonNode request, JsonNode response) {
    currentState.setId(response.path("id").intValue());
    currentState.setName(request.path("name").textValue());
    currentState.setOrigin(request.path("origin").textValue());

    String state = request.path("State").textValue();
    if (state != null)
      currentState.setState(State.valueOf(state));
  }

}
