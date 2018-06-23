package com.company.app.Singleton;

public class JavaSingleton {
  private static JavaSingleton instance = null;
  private JavaSingleton() {

  }
  public static JavaSingleton getInstance() {
    if(instance == null) {
      instance = new JavaSingleton();
    }
    return instance;
  }
}
