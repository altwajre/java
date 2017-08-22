package com.company.app;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionTest {
  @Test
  public void testInvokeSettersGenericMethod() throws Exception {
    final Object obj1 = SetterUtils.newInstance(Person.class);
    final Object obj2 = SetterUtils.newInstance(Person.class);
    Assert.assertEquals(obj1, obj2);
  }

  @Test
  public void testSetPrivateField() throws Exception {
    Class<?> clazz = Class.forName("com.company.app.Person");
    Object objInstance = clazz.newInstance();
    System.out.println(objInstance.getClass().getSimpleName());
    for (Field field : clazz.getDeclaredFields()) {
      final String fieldName = field.getName();
      System.out.println(fieldName);
      if (fieldName == "name") {
        field.setAccessible(true); // NOTE: setAccessible(true) before setting private field
        field.set(objInstance, "NewName");
      }
    }
    Person person = (Person) objInstance;
    System.out.println(person);
  }

  @Test
  public void testInvokeSetterMethods() throws Exception {
    Class<?> clazz = Class.forName("com.company.app.Person");
    Object objInstance = clazz.newInstance();

    for (Method method : clazz.getDeclaredMethods()) {
      System.out.println(method.getName());
    }

    System.out.println("#excludeMethods");

    final List<String> excludeMethods = Arrays.asList("equals", "toString", "hashCode", "canEqual");
    final Map<String, Object> paramValues = new HashMap<String, Object>(){{
      put("java.lang.String", "string");
      put("int", 8);
    }};
    Arrays.stream(clazz.getDeclaredMethods()).filter(m -> {
      final String methodName = m.getName();
      return !excludeMethods.contains(methodName) && methodName.startsWith("set");
    }).forEach(m ->{
      System.out.println(m.getName());
      for (Class<?> paramType : m.getParameterTypes()) {
        System.out.println(paramType.getTypeName());
      }
      final String typeName = m.getParameterTypes()[0].getTypeName();
      final Object paramValue = paramValues.get(typeName);
      try {
        m.invoke(objInstance, paramValue);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    });

    final Person person = (Person) objInstance;
    System.out.println(person);

  }

}
