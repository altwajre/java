package com.company.app;

public class BooleanQuestion extends Question {
  public BooleanQuestion(int id, String text) {
    super(id, text, new String[]{"No", "yes"});
  }

  @Override
  public boolean match(int expected, int actual) {
    return expected == actual;
  }
}
