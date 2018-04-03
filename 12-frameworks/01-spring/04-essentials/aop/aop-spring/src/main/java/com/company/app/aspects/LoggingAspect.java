package com.company.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

//  @Before("allPersonMethods()")
  @Before("execution(* com.company.app.models.*.*(..))")
  public void loggingAdvice(JoinPoint joinPoint) {
    logger.info("### Allowed execution for {}", joinPoint);
  }

  @Pointcut("within(com.company.app.models.*)")
  public void allPersonMethods() {
  }

  @Pointcut("within(com.company.app.implementations.EnglishGreetingService)")
  public void allEnglishGreetingServiceMethods() {
  }
}
