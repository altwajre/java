package com.company.app.services.controller;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.implementations.factories.WhiskyScenarioFactoryImpl;
import com.company.app.services.TestResult;
import com.company.app.services.TestStep;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class WhiskyScenarioController {
  private Map<Integer, TestResult> testResults = new LinkedHashMap<>();
  private Map<Integer, TestStep> testScenarios = new LinkedHashMap<>();

  @PostMapping(value = "/whiskies/scenario")
  public ResponseEntity<TestResult> testScenario(@RequestBody TestStep testStep) {
    int id = testStep.getId();
    testScenarios.put(id, testStep);
    TestResult testResult = new TestResult();

    try {
      RestAssured.baseURI = "http://localhost";
      RestAssured.port = Integer.getInteger("http.port", 8080);

      WhiskyScenario whisky = new WhiskyScenarioFactoryImpl().create();
      testStep.getSteps().forEach(s -> {
        whisky.invoke(s);
      });
      testResult.setStatus("PASS");
    } catch (Exception e) {
      testResult.setStatus("FAIL");
      testResult.setError(e.getMessage());
//      testResult.setError(e.getMessage() + ": " + e.getCause().getCause().toString()); // null check
    } finally {
      RestAssured.reset();
    }

    return new ResponseEntity(testResult, HttpStatus.OK);
  }

  @PostMapping(value = "/whiskies/jsonnode")
  public ResponseEntity<String> testJson(@RequestBody TestStep testStep) {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode whisky = mapper.createObjectNode();
    ((ObjectNode) whisky).put("name", "Nikka");
    ((ObjectNode) whisky).put("origin", "Japanese");

    System.out.println(whisky);

    return new ResponseEntity<>(whisky.toString(), HttpStatus.OK);

  }

}
