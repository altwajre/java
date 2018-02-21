package com.company.app.implementations.factory;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factory.WhiskyClientFactory;
import com.company.app.implementations.WhiskyValidator;

public class WhiskyValidatorClientFactory implements WhiskyClientFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyValidator(new WhiskyClientClientFactory().create());
  }
}
