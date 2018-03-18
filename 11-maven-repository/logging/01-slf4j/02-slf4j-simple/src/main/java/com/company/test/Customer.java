package com.company.test;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@RequiredArgsConstructor
public class Customer {
  Logger logger = LoggerFactory.getLogger(Customer.class);

  @NonNull
  private String name;

  public void say(String msg){
    logger.info(this.name + " say: " + msg);
  }
}
