package com.company.app;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Person {
    private String name;
    private int age;
}

public class App {
    public static void main(String[] args) {
        Person tom = Person
                .builder()
                .name("Tom")
                .age(10)
                .build();
        System.out.println(tom);
    }
}
/*
pom.xml

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.16.6</version>
      </dependency>
    </dependencies>
  </dependencyManagement>

output:
Person(name=Tom, age=10)
 */