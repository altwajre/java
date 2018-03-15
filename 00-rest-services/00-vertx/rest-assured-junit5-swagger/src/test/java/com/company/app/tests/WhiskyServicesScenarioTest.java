package com.company.app.tests;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.implementations.factories.WhiskyScenarioFactoryImpl;
import com.jayway.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WhiskyServicesScenarioTest {

  private WhiskyScenario whisky;

  @BeforeAll
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
  }

  @AfterAll
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @BeforeEach
  public void setUp() {
    whisky = new WhiskyScenarioFactoryImpl().create();
  }

  // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
  @Test
  public void createTest() {
    whisky.create();
    whisky.get();
  }

  // curl -X PUT http://localhost:8080/api/whiskies/{id} -d '{"name": "Bowmore 18", "origin": "Scotland"}'
  @Test
  public void updateTest() {
    whisky.create();
    whisky.update();
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/activate -d {}
  @Test
  public void activateTest() {
    whisky.create();
    whisky.activate();
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/suspend -d {}
  @Test
  public void suspendTest() {
    whisky.create();
    whisky.suspend();
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/unsuspend -d {}
  @Test
  public void unsuspendTest() {
    whisky.create();
    whisky.suspend();
    whisky.unsuspend();
  }

  // curl -X POST http://localhost:8080/api/whiskies/{id}/cancel -d {}
  @Test
  public void cancelTest() {
    whisky.create();
    whisky.cancel();
  }

  // curl -X DELETE http://localhost:8080/api/whiskies/{id}
  @Test
  public void deleteTest() {
    whisky.create();
    whisky.delete();
  }

}
