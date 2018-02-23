package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.contracts.factories.WhiskyScenarioFactory;
import com.company.app.implementations.WhiskyScenarioImpl;

public class WhiskyScenarioClientFactory implements WhiskyScenarioFactory {
  @Override
  public WhiskyScenario create() {
    return new WhiskyScenarioImpl(new WhiskyValidatorClientFactory().create());
  }
}
