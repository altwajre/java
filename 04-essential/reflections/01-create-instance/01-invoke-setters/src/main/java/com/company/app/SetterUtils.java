package com.company.app;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetterUtils {
  public static Object newInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
    Object objInstance = clazz.newInstance();

    final List<String> excludeMethods = Arrays.asList("equals", "toString", "hashCode", "canEqual");
    final Map<String, Object> paramValues = new HashMap<String, Object>(){{
      put("int", 8);
    }};
    Arrays.stream(clazz.getDeclaredMethods()).filter(m -> {
      final String methodName = m.getName();
      return !excludeMethods.contains(methodName) && methodName.startsWith("set");
    }).forEach(m ->{
      final String typeName = m.getParameterTypes()[0].getTypeName();
      Object paramValue;
      if(typeName == "java.lang.String") {
        paramValue = m.getName().substring(3);
      }
      else {
        paramValue = paramValues.get(typeName);
      }
      try {
        m.invoke(objInstance, paramValue);
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    });

    return objInstance;
  }
}
