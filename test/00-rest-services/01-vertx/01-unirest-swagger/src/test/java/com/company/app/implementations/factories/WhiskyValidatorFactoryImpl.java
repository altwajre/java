package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factories.TestFactory;
import com.company.app.implementations.clients.WhiskyValidator;

public class WhiskyValidatorFactoryImpl implements TestFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyValidator(new WhiskyRestAssuredClientFactory().create());
  }
}
