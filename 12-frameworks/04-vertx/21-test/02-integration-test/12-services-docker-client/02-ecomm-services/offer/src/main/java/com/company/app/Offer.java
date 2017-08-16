package com.company.app;

import lombok.Data;

@Data
public class Offer {
  private int id;
  private String name;
  private Product product;
  public Offer(){

  }
  public Offer(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
