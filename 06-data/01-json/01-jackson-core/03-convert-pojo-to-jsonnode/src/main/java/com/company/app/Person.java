package com.company.app;

import lombok.Data;

import java.util.Map;

@Data
public class Person {
  private Long id;
  private String name;
  private int age;
  private String[] friends;
  private Map<String, String> keys;
}
