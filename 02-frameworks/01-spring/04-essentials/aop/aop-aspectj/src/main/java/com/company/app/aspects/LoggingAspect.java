package com.company.app.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

  //  @Before("allPersonMethods()")
  @Before("execution(* com.company.app.models.*.*(..))")
  public void loggingAdvice(JoinPoint joinPoint) {
    System.out.println("### Allowed execution for {}");
  }

  @Pointcut("within(com.company.app.models.*)")
  public void allPersonMethods() {
  }
}
