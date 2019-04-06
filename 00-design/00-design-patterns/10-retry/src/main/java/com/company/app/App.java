package com.company.app;

public class App {
  public static void main(String[] args) {
    final RuntimeException e = new RuntimeException("unhandled");
    final Retry<String> retry = new Retry<>(
        () -> {
          System.out.println("BusinessOperation Impl");
          return "pass";
//          throw e;
        },
        3,
        100
    );
    try {
      String result = retry.perform();
      System.out.println("result: "+result);
    } catch (RuntimeException ex) {
      //ignore
    }

    System.out.println(retry.errors());
  }
}
