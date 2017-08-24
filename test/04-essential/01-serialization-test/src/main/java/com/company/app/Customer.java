package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
@AllArgsConstructor
@Builder
public class Customer {
  @JsonProperty
  private String name;
  @JsonProperty
  private int age;
}
