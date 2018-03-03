package com.company.app.tests;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.implementations.factories.WhiskyScenarioFactoryImpl;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WhiskyServicesDatadrivenTest {

  private static WhiskyScenario whisky;

  @BeforeAll
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
    whisky = new WhiskyScenarioFactoryImpl().create();
  }

  @AfterAll
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @DisplayName("Whisky: create")
  @ParameterizedTest
  @ValueSource(strings = {"create"})
  public void create(String methodName) {
    whisky.invoke(methodName);
  }

  @DisplayName("Whisky: create update")
  @ParameterizedTest
  @ValueSource(strings = {"create", "update"})
  public void create_Update(String methodName) {
    whisky.invoke(methodName);
  }

  @DisplayName("Whisky: create suspend")
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
