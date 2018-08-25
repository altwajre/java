package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.contracts.factories.WhiskyScenarioFactory;
import com.company.app.implementations.WhiskyScenarioImpl;

public class WhiskyScenarioFactoryImpl implements WhiskyScenarioFactory {
  @Override
  public WhiskyScenario create() {
    return new WhiskyScenarioImpl(new WhiskyValidatorFactoryImpl().create());
  }
}
