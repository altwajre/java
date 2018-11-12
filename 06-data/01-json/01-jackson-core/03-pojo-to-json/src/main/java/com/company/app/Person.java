package com.company.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(Include.NON_NULL)
public class Person {
  private Long id;
  private String name;
  private int age;
  private String[] friends;
  private List<String> books;
  private Map<String, String> keys;
  private List<OfferContainer> offers;
}
