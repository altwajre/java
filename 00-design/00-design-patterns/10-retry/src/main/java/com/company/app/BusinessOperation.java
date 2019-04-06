package com.company.app;

@FunctionalInterface
public interface BusinessOperation<T> {
  T perform();
}
