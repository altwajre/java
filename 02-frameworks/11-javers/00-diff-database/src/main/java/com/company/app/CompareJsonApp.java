package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

public class CompareJsonApp {
  public static void main(String[] args) {
    Javers javers = JaversBuilder.javers().build();

    JsonNode json1 = ResourceHelper.get("data/customer.json");
    JsonNode json2 = ResourceHelper.get("data/customer.json");

    Diff diffJson = javers.compare(json1, json2);
    System.out.println(diffJson);

  }
}
