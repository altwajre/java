package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResourceHelper {

  public static JsonNode get(String path) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

//    mapper.readTree("{\"name\":\"Nikka\",\"origin\":\"Japanese\"}")
//    mapper.readTree(new File("src/test/resources/data/testCreate-whisky.json"))
    return mapper.readTree(mapper
        .getClass()
        .getClassLoader()
        .getResourceAsStream(path));

  }

}
