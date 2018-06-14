package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factories.WhiskyClientFactory;
import com.company.app.implementations.WhiskyRestAssuredClient;

public class WhiskyRestAssuredClientFactory implements WhiskyClientFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyRestAssuredClient();
  }
}
