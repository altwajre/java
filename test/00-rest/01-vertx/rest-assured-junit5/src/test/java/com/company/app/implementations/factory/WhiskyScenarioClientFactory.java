package com.company.app.implementations.factory;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.contracts.factory.WhiskyScenarioFactory;
import com.company.app.implementations.WhiskyClientImpl;
import com.company.app.implementations.WhiskyScenarioImpl;
import com.company.app.implementations.WhiskyValidator;
import com.company.app.implementations.WhiskyValidatorBase;

public class WhiskyScenarioClientFactory implements WhiskyScenarioFactory {
  @Override
  public WhiskyScenario create() {
    WhiskyValidatorBase whiskyValidator = new WhiskyValidator(new WhiskyClientImpl());
    WhiskyScenarioImpl whisky = new WhiskyScenarioImpl(whiskyValidator);

    return new WhiskyScenarioImpl(new WhiskyValidatorClientFactory().create());
  }
}
