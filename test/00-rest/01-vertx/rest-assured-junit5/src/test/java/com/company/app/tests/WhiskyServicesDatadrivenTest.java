package com.company.app.tests;

import com.company.app.implementations.WhiskyClientImpl;
import com.company.app.implementations.WhiskyScenarioProxy;
import com.company.app.implementations.WhiskyValidator;
import com.company.app.implementations.WhiskyValidatorBase;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WhiskyServicesDatadrivenTest {

  private static WhiskyScenarioProxy whisky;

  @BeforeAll
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
    WhiskyValidatorBase whiskyValidator = new WhiskyValidator(new WhiskyClientImpl());
    whisky = new WhiskyScenarioProxy(whiskyValidator);
  }

  @AfterAll
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @DisplayName("DisplayName: create")
  @ParameterizedTest
  @ValueSource(strings = {"create"})
  public void create(String methodName) {
    whisky.invoke(methodName);
  }

  @DisplayName("DisplayName: create update")
  @ParameterizedTest
  @ValueSource(strings = {"create", "update"})
  public void create_Update(String methodName) {
    whisky.invoke(methodName);
  }

  @DisplayName("DisplayName: create suspend")
  @ParameterizedTest
  @ValueSource(strings = {"create", "suspend"})
  public void create_Suspend(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "suspend", "unsuspend"})
  public void create_Suspend_Unsuspend(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "suspend", "unsuspend", "suspend", "unsuspend"})
  public void repeat_Suspend_Unsuspend_Twice(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "cancel"})
  public void create_Cancel(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "cancel", "activate"})
  public void create_Cancel_Activate(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "cancel", "activate", "cancel", "activate"})
  public void repeat_Cancel_Activate_Twice(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "suspend", "unsuspend", "cancel", "activate"})
  public void suspend_Unsuspend_Cancel_Activate(String methodName) {
    whisky.invoke(methodName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"create", "delete"})
  public void create_Delete(String methodName) {
    whisky.invoke(methodName);
  }

}
