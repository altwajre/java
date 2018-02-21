package com.company.app.implementations.factory;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.contracts.factory.WhiskyScenarioFactory;
import com.company.app.implementations.WhiskyScenarioImpl;

public class WhiskyScenarioClientFactory implements WhiskyScenarioFactory {
  @Override
  public WhiskyScenario create() {
    return new WhiskyScenarioImpl(new WhiskyValidatorClientFactory().create());
  }
}
