package com.company.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)
public class Offer {
  private String name;
  private List<ProductContainer> products;
  private String uri;
}
