package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResourceHelper {

  public static JsonNode get(String path) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    return mapper.readTree(mapper
        .getClass()
        .getClassLoader()
        .getResourceAsStream(path));

  }

}
