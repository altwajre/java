package com.company.app;

import org.junit.Assert;

public class EqualityValidator {
  public static <T> void validate(Class<T> clazz) throws IllegalAccessException, InstantiationException, NullPointerException {
    if (clazz == null) {
      throw new NullPointerException("Can not validate null class");
    }
    Assert.assertEquals(SetterUtils.INSTANCE.newInstance(clazz), SetterUtils.INSTANCE.newInstance(clazz));
  }
}
