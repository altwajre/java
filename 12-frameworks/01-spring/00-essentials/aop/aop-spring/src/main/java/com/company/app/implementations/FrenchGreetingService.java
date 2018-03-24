package com.company.app.implementations;

import com.company.app.interfaces.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class FrenchGreetingService implements GreetingService {
  @Override
  public String greet() {
    return "FrenchGreetingService: Hello World!";
  }
}
