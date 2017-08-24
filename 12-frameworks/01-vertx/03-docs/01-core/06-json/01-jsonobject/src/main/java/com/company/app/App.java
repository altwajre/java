package com.company.app;

import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
http://vertx.io/docs/vertx-core/java/#_json_objects

The JsonObject class represents JSON objects.

A JSON object is basically just a map which has string keys and values can be of one of the JSON supported types (string, number, boolean).

JSON objects also support null values.
 */
@Data
@AllArgsConstructor
class User {
  public User() {
  }

  private String name;
  private Integer age;
}

public class App {
  public static void main(String[] args) {
    createFromString();

    createFromMap();

    putAndGet();

    mapFromPojo();

    mapToPojo();

  }

  private static void mapFromPojo() {
    System.out.println("#mapFromPojo");
    User user = new User("Tom", 38);
    JsonObject jsonObject = JsonObject.mapFrom(user);
    System.out.println(jsonObject);
    System.out.println(jsonObject.getString("name"));
    System.out.println(jsonObject.getInteger("age"));
  }

  private static void mapToPojo() {
    System.out.println("#mapToPojo");
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("name", "Tom").put("age", 28);
    User user = jsonObject.mapTo(User.class);
    System.out.println(user);
  }

  private static void putAndGet() {
    System.out.println("#putAndGet");
    JsonObject jsonObject = new JsonObject();
    jsonObject.put("name", "Tom").put("age", 28);
    System.out.println(jsonObject);
    System.out.println(jsonObject.getString("name"));
    System.out.println(jsonObject.getInteger("age"));
  }

  private static void createFromMap() {
    System.out.println("#createFromMap");
    Map<String, Object> map = new HashMap<>();
    map.put("name", "Tom");
    map.put("age", 18);
    JsonObject jsonObject = new JsonObject(map);
    System.out.println(jsonObject);
    System.out.println(jsonObject.getString("name"));
    System.out.println(jsonObject.getInteger("age"));
  }

  private static void createFromString() {
    System.out.println("#createFromString");
    String jsonString = "{\"name\":\"Tom\"}";
    JsonObject jsonObject = new JsonObject(jsonString);
    System.out.println(jsonObject);
    System.out.println(jsonObject.getString("name"));
  }
}
/*
output:
#createFromString
{"name":"Tom"}
Tom
#createFromMap
{"name":"Tom","age":18}
Tom
18
#putAndGet
{"name":"Tom","age":28}
Tom
28
#mapFromPojo
{"name":"Tom","age":38}
Tom
38
#mapToPojo
User(name=Tom, age=28)
 */
