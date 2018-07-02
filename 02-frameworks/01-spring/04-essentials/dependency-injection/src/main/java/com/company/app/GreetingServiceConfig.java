package com.company.app;

import com.company.app.implementations.EnglishGreetingService;
import com.company.app.implementations.FrenchGreetingService;
import com.company.app.interfaces.GreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GreetingServiceConfig {

  @Bean
  @Primary
  @ConditionalOnProperty(name = "language.name", havingValue = "english", matchIfMissing = true)
  public GreetingService englishGreetingService() {
    return new EnglishGreetingService();
  }

  @Bean
  @ConditionalOnProperty(name = "language.name", havingValue = "french")
  public GreetingService frenchGreetingService() {
    return new FrenchGreetingService();
  }
}
