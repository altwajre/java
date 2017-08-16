package com.company.app;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
class User {
  private String name;
  private int age;
}

public class App {
  public static void main(String[] args) {
    String users = "[{\"name\":\"Tom\",\"age\":18},{\"name\":\"Dick\",\"age\":28}]";

    // convert JsonString to JsonArray
    JsonArray array = new JsonArray(users);
    System.out.println("Users JsonArray: "+array);

    // convert each json to pojo
    array.forEach(j -> {
      User user = ((JsonObject) j).mapTo(User.class);
      System.out.println("User POJO: " + user);
    });

  }
}
/*
Users JsonArray: [{"name":"Tom","age":18},{"name":"Dick","age":28}]
User POJO: User(name=Tom, age=18)
User POJO: User(name=Dick, age=28)
 */
