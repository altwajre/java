package com.company.app.contracts;

public interface WhiskyScenario {
  void get();
  void getAll();
  void create();
  void update();
  void activate();
  void suspend();
  void unsuspend();
  void cancel();
  void delete();
}
