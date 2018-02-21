package com.company.app.implementations.factory;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factory.WhiskyClientFactory;
import com.company.app.implementations.WhiskyClientImpl;

public class WhiskyClientClientFactory implements WhiskyClientFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyClientImpl();
  }
}
