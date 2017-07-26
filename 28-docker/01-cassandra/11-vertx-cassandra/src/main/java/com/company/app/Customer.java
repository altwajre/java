package com.company.app;

import com.datastax.driver.mapping.annotations.Table;
import lombok.Data;

@Data
@Table(name="customer")
public class Customer {
  private String id;
  private String name;
  private String age;
}
