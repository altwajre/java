package com.company.app.contracts.factory;

import com.company.app.contracts.WhiskyClient;

// factory acts as dependency injection that controls how the client validator should be built
public interface WhiskyClientFactory {
  WhiskyClient create();
}
