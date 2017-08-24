package com.company.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum SetterUtils {
  INSTANCE;

  private static final Logger LOG = LoggerFactory.getLogger(SetterUtils.class);

  public <T> T newInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException, NullPointerException {
    if (clazz == null) {
      throw new NullPointerException("Can not create a new instance object from null class");
    }
    T objInstance = clazz.newInstance();
    final Map<String, Object> paramValues = new HashMap<String, Object>() {{
      put(int.class.getTypeName(), 8);
    }};
    Arrays.stream(clazz.getDeclaredMethods())
        .filter(m -> {
          final String methodName = m.getName();
          return methodName.startsWith("set");
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
            LOG.error(e.getMessage(), e);
          } catch (InvocationTargetException e) {
            LOG.error(e.getMessage(), e);
          }
        });
    return objInstance;
  }
}
