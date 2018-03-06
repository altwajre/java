package com.company.app.tools;

import com.company.app.implementations.WhiskyRestAssuredClient;
import com.company.app.implementations.WhiskyScenarioImpl;
import com.company.app.implementations.WhiskyValidator;
import com.company.app.implementations.WhiskyValidatorBase;
import com.jayway.restassured.RestAssured;

import java.util.stream.IntStream;

public class SeedDataApp {
  public static void main(String[] args) {
    try{
      RestAssured.baseURI = "http://localhost";
      RestAssured.port = Integer.getInteger("http.port", 8080);

      WhiskyValidatorBase whiskyValidator = new WhiskyValidator(new WhiskyRestAssuredClient());
      WhiskyScenarioImpl whisky = new WhiskyScenarioImpl(whiskyValidator);

      IntStream
          .range(0, 10)
          .parallel()
          .forEach(i -> {
            whisky.create();
          });

      // Use GET all to verify
      // curl http://localhost:8080/api/whiskies
    }
    finally {
      RestAssured.reset();
    }
  }
}
