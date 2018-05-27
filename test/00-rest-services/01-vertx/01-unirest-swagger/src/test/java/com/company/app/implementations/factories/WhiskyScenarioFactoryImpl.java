package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyScenario;
import com.company.app.contracts.factories.TestFactory;
import com.company.app.implementations.clients.WhiskyScenarioImpl;

public class WhiskyScenarioFactoryImpl implements TestFactory {
  @Override
  public WhiskyScenario create() {
    return new WhiskyScenarioImpl(new WhiskyValidatorFactoryImpl().create());
  }
}
