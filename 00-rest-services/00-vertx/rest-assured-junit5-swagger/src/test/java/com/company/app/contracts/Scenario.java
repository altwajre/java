package com.company.app.contracts;

import java.lang.reflect.InvocationTargetException;

public interface Scenario {
  default void invoke(String methodName){
    try {
      this.getClass().getDeclaredMethod(methodName).invoke(this);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException(e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
