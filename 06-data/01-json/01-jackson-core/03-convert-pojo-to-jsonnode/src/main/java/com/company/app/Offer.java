package com.company.app;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class Offer {
  private String name;
  private List<ProductContainer> products;
}
