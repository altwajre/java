package com.company.app.tests;

import com.company.app.common.GlobalMapper;
import com.company.app.common.UnirestHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class UnirestTest {

  private ObjectMapper mapper = GlobalMapper.INSTANCE.mapper();
  private String baseUri = "http://localhost:8080";

  @Test
  public void createTest() {
    JsonNode requestBody = mapper.createObjectNode();
    ((ObjectNode) requestBody).put("name", "Nikka");
    ((ObjectNode) requestBody).put("origin", "Japanese");

    JsonNode response = UnirestHelper.post(baseUri + "/api/whiskies", null, requestBody, HttpStatus.SC_CREATED);
    System.out.println(response);
  }

}
