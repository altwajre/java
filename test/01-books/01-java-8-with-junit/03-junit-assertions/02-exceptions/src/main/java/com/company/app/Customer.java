package com.company.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {
  private List<Account> accounts = new ArrayList<>();
  public void add(Account account) {
    accounts.add(account);
  }
  Iterator<Account> getAccounts() {
    return accounts.iterator();
  }
}
