package com.company.app.implementations;

import com.company.app.interfaces.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class EnglishGreetingService implements GreetingService {
  @Override
  public String greet() {
    return "EnglishGreetingService: Hello World!";
  }
}
