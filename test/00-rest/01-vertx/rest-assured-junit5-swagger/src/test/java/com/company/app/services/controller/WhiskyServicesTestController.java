package com.company.app.services.controller;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.implementations.factories.WhiskyScenarioClientFactory;
import com.company.app.services.TestResult;
import com.company.app.services.TestStep;
import com.jayway.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WhiskyServicesTestController {
  private Map<Integer, TestResult> testResults = new LinkedHashMap<>();
  private Map<Integer, TestStep> testScenarios = new LinkedHashMap<>();

  @PostMapping(value = "/whiskies")
  public ResponseEntity<TestResult> create(@RequestBody TestStep testStep) {
    int id = testStep.getId();
    testScenarios.put(id, testStep);
    TestResult testResult = new TestResult();

    try {
      RestAssured.baseURI = "http://localhost";
      RestAssured.port = Integer.getInteger("http.port", 8080);

      WhiskyScenario whisky = new WhiskyScenarioClientFactory().create();
      testStep.getSteps().forEach(s -> {
        whisky.invoke(s);
      });
      testResult.setStatus("PASS");
    }
    catch (Exception e) {
      testResult.setStatus("FAIL");
      testResult.setError(e.getMessage());
//      testResult.setError(e.getMessage() + ": " + e.getCause().getCause().toString()); // null check
    }
    finally {
      RestAssured.reset();
    }

    return new ResponseEntity(testResult, HttpStatus.OK);
  }

}
