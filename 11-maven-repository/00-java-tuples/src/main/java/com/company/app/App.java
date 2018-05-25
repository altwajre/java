package com.company.app;

import org.javatuples.Pair;

public class App {
  public static void main(String[] args) {

    Runnable runnable = () -> {
      System.out.println("runnable is invoked");
    };

    runnable.run();

    Pair<String, Integer> person = new Pair<>("Tom", 28);
    System.out.println(person);
    String name = person.getValue0();
    System.out.println(name);
    Integer age = person.getValue1();
    System.out.println(age);
  }
}
