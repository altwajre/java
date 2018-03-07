package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.junit.Test;

public class CompareTwoJsonNodes {
  @Test
  public void compareTwoJsonNodesTest() {
    ObjectMapper mapper = new ObjectMapper();

    JsonNode baseline = mapper.createObjectNode();
    ((ObjectNode) baseline).put("name", "Nikka");
    ((ObjectNode) baseline).put("origin", "Japanese");
    String oldJsonStr = baseline.toString();
    System.out.println(oldJsonStr);

    JsonNode change = mapper.createObjectNode();
    ((ObjectNode) change).put("name", "Nikka");
    ((ObjectNode) change).put("origin", "Japan");
    String newJsonStr = change.toString();
    System.out.println(newJsonStr);

    Javers javers = JaversBuilder.javers().build();
    Diff diff = javers.compare(baseline, change);
    System.out.println(diff.toString());

  }
  /*
  output:
  Diff:
1. ValueChange{globalId:'com.fasterxml.jackson.databind.node.ObjectNode/#_children/origin', property:'_value', oldVal:'Japanese', newVal:'Japan'}
   */
}
