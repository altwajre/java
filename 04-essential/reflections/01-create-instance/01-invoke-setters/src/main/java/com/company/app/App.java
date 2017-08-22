package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Data
@NoArgsConstructor
class Person {
  private String name;
  private int age;
}

@Data
@AllArgsConstructor
class Customer {
  private String name;
  private int age;
}

/*
checkout ReflectionTest.testInvokeSettersGenericMethod()
 */
public class App {
  public static void main(String[] args) throws Exception {
    testSetterUtils();

    testNoArgsConstructor();

    testAllArgsConstructor();
  }

  private static void testSetterUtils() throws InstantiationException, IllegalAccessException {
    System.out.println("#testSetterUtils");
    final Object person = SetterUtils.newInstance(Person.class);
    System.out.println(person);
  }

  private static void testAllArgsConstructor() throws Exception {
    System.out.println("# testAllArgsConstructor");
    Class<?> clazz = Class.forName("com.company.app.Customer");
    Constructor<?> ctor = clazz.getConstructor(String.class, int.class);
    Object object = ctor.newInstance(new Object[]{"Tom", 8});
    System.out.println(object.getClass().getSimpleName());
    Customer customer = (Customer) object;
    System.out.println("customer name=" + customer.getName());
  }

  private static void testNoArgsConstructor() throws Exception {
    System.out.println("# testNoArgsConstructor");
    Class<?> clazz = Class.forName("com.company.app.Person");
    Object object = clazz.newInstance();
    System.out.println(object.getClass().getSimpleName());
    for (Field field : clazz.getDeclaredFields()) {
      final String fieldName = field.getName();
      System.out.println(fieldName);
      if (fieldName == "name") {
        field.setAccessible(true);
        field.set(object, "NewName");
      }
    }
    Person person = (Person) object;
    System.out.println(person);
  }
}
/*
#testSetterUtils
Person(name=string, age=8)
# testNoArgsConstructor
Person
name
age
Person(name=NewName, age=0)
# testAllArgsConstructor
Customer
customer name=Tom
 */
