package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.Test;

public class CompareTwoJsonStrings {
  @Test
  public void compareTwoJsonStringsTest() {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode old = mapper.createObjectNode();
    ((ObjectNode) old).put("name", "Nikka");
    ((ObjectNode) old).put("origin", "Japanese");
    String oldJsonStr = old.toString();
    System.out.println(oldJsonStr);

    JsonNode current = mapper.createObjectNode();
    ((ObjectNode) current).put("name", "Nikka");
    ((ObjectNode) current).put("origin", "Japan");
    String curJsonStr = current.toString();
    System.out.println(curJsonStr);

    Javers javers = JaversBuilder.javers().build();
    Diff diff = javers.compare(old, current);
    System.out.println(diff.toString());

  }
  /*
  output:
  Diff:
1. ValueChange{globalId:'com.fasterxml.jackson.databind.node.ObjectNode/#_children/origin', property:'_value', oldVal:'Japanese', newVal:'Japan'}
   */
}
