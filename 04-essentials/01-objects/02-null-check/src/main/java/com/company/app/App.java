package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
class User {
  private String name;
  private int age;
}

class UserDao {
  private User user;

  public UserDao() {

  }

  public UserDao(User user) {
    this.user = user;
  }

  public String getUserName() {
    return Objects.isNull(user) ? null : user.getName(); // HERE - null check
  }
}

public class App {
  public static void main(String[] args) {
    testNotNull();

    testNull();
  }

  private static void testNull() {
    System.out.println("#testNull");
    UserDao userDao = new UserDao();
    System.out.println(userDao.getUserName());
  }
/*
#testNull
null
 */

  private static void testNotNull() {
    System.out.println("#testNotNull");
    User user = new User("Tom", 18);
    UserDao userDao = new UserDao(user);
    System.out.println(userDao.getUserName());
  }
/*
#testNotNull
Tom
 */
}
