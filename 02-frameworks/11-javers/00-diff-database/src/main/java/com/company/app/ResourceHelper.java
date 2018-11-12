package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResourceHelper {

  public static JsonNode get(String path) {

    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readTree(mapper
          .getClass()
          .getClassLoader()
          .getResourceAsStream(path));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}
