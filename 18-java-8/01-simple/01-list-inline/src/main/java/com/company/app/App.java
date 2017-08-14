package com.company.app;

import java.util.Arrays;
import java.util.List;

public class App {
  public static void main(String[] args) {
    List<String> names = Arrays.asList("Tom", "Dick", "Harry");
    names.forEach(System.out::println);
  }
}
