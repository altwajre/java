package com.company.app.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResourceHelper {

  public static JsonNode get(String path) {

    ObjectMapper mapper = GlobalMapper.INSTANCE.mapper();

//    mapper.readTree("{\"name\":\"Nikka\",\"origin\":\"Japanese\"}")
//    mapper.readTree(new File("src/test/resources/data/testCreate-whisky.json"))
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
