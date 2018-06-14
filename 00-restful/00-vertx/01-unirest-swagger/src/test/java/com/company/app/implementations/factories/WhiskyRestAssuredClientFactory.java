package com.company.app.implementations.factories;

import com.company.app.contracts.WhiskyClient;
import com.company.app.contracts.factories.TestFactory;
import com.company.app.implementations.clients.WhiskyRestAssuredClient;

public class WhiskyRestAssuredClientFactory implements TestFactory {
  @Override
  public WhiskyClient create() {
    return new WhiskyRestAssuredClient();
  }
}
