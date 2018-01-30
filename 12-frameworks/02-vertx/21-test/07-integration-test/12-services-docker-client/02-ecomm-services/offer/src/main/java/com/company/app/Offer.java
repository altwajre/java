package com.company.app;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Offer {
  private int id;
  private String name;
  private Product product;

  public Offer(int id, String name) {
    this.id = id;
    this.name = name;
  }
}
