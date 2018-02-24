package com.company.app.implementations;

import com.company.app.common.CreateMethodShouldBeCalledFirstException;
import com.company.app.common.State;
import com.company.app.common.WhiskyPayload;
import com.company.app.contracts.Validator;
import com.company.app.contracts.WhiskyClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;

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
  public JsonObject get(String id) {
    System.out.println("WhiskyValidator.get()");
    JsonObject actual = super.get(id);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject getAll() {
    JsonObject actual = super.getAll();
    return actual;
  }

  @Override
  public JsonObject create(JsonObject body) {
    currentState = new WhiskyPayload(); // create() is the starting point. Test should fail without create() first.

    System.out.println("WhiskyValidator.create()");
    JsonObject actual = super.create(body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject update(String id, JsonObject body) {
    System.out.println("WhiskyValidator.update()");

    if (isNull(currentState))
      throw new CreateMethodShouldBeCalledFirstException();

    JsonObject actual = super.update(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject activate(String id, JsonObject body) {
    System.out.println("WhiskyValidator.activate()");

    if (isNull(currentState))
      throw new CreateMethodShouldBeCalledFirstException();

    JsonObject actual = super.activate(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject suspend(String id, JsonObject body) {
    System.out.println("WhiskyValidator.suspend()");

    if (isNull(currentState))
      throw new CreateMethodShouldBeCalledFirstException();

    JsonObject actual = super.suspend(id, body);
    currentState.setState(State.SUSPENDED);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject unsuspend(String id, JsonObject body) {
    System.out.println("WhiskyValidator.unsuspend()");

    if (isNull(currentState))
      throw new CreateMethodShouldBeCalledFirstException();

    JsonObject actual = super.unsuspend(id, body);
    currentState.setState(State.ACTIVE);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public JsonObject cancel(String id, JsonObject body) {
    System.out.println("WhiskyValidator.cancel()");

    if (isNull(currentState))
      throw new CreateMethodShouldBeCalledFirstException();

    JsonObject actual = super.cancel(id, body);
    currentState.setState(State.CANCELLED);
    captureCurrentState(body, actual);
    validate(mapper.convertValue(currentState, JsonObject.class), actual);
    return actual;
  }

  @Override
  public String delete(String id) {
    System.out.println("WhiskyValidator.delete()");
    String actual = super.delete(id);
    return actual;
  }

  @Override
  public void validate(JsonObject expected, JsonObject actual) {
    String name = expected.getString("name");
    if (name != null)
      assertThat(actual.getString("name")).isEqualToIgnoringCase(name);

    String origin = expected.getString("origin");
    if (origin != null)
      assertThat(actual.getString("origin")).isEqualToIgnoringCase(origin);

    String state = expected.getString("state");
    if (state != null)
      assertThat(actual.getString("state")).isEqualToIgnoringCase(state);
  }

  private void captureCurrentState(JsonObject request, JsonObject response) {
    currentState.setId(response.getInteger("id"));
    currentState.setName(request.getString("name"));
    currentState.setOrigin(request.getString("origin"));

    String state = request.getString("State");
    if (state != null)
      currentState.setState(State.valueOf(state));
  }

}
