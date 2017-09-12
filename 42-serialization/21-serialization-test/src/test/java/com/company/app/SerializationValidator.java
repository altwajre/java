package com.company.app;

import org.junit.Assert;

import java.io.IOException;

public class SerializationValidator {
  public static void validate(Object value) throws IOException {
    if(value == null) {
      throw new IllegalArgumentException("Can not serialize null object");
    }
    final String serializedString = GlobalMapper.INSTANCE.mapper().writeValueAsString(value);
    final Object deserializedObj = GlobalMapper.INSTANCE.mapper().readValue(serializedString, value.getClass());
    Assert.assertEquals(value, deserializedObj);
  }
  public static <T> void validateClone(T value, Class<T> type) {
    if(value == null) {
      throw new IllegalArgumentException("Can not serialize null object");
    }
    final T deserializedObj = GlobalMapper.INSTANCE.clone(value, type);
    Assert.assertEquals(value, deserializedObj);
  }
}
