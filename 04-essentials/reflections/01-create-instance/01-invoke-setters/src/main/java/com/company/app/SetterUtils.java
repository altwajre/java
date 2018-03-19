package com.company.app;

import com.google.common.collect.ImmutableMap;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter values
 * 1, String setter: setter name will be the parameter value
 * 2, Other parameter values are stored at paramValues
 */
public enum SetterUtils {
  INSTANCE;
  public <T> T newInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {
    if (clazz == null) {
      throw new IllegalArgumentException("Can not create a new instance object from null class");
    }
    final List<String> excludeMethods = Arrays.asList("equals", "toString", "hashCode", "canEqual");
    // DO NOT use ImmutableMap
//    final ImmutableMap<String, Object> paramValues = new ImmutableMap.Builder<String, Object>()
//        .put(int.class.getTypeName(), 8)
//        .build();
    final Map<String, Object> paramValues = new HashMap<String, Object>() {{
      put(int.class.getTypeName(), 8);
    }};
    T objInstance = clazz.newInstance();
    Arrays.stream(clazz.getDeclaredMethods())
        .filter(m -> {
          final String methodName = m.getName();
          return !excludeMethods.contains(methodName) && methodName.startsWith("set");
        })
        .forEach(m -> {
          final String typeName = m.getParameterTypes()[0].getTypeName();
          Object paramValue;
          if (typeName.equals(String.class.getTypeName())) {
            paramValue = m.getName().substring(3);
          } else {
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
