package com.company.app;

public interface Stack {
  boolean isEmpty();

  int getSize();

  void push(int element);

  int pop();

  int top();

  Integer find(int element);

  class IllegalCapacity extends RuntimeException {
  }

  class Empty extends RuntimeException {
  }
}
