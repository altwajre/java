package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

  @Column(name = "ADDRESS_LINE_1")
  private String addressLine1;

  @Column(name = "ADDRESS_LINE_2")
  private String addressLine2;

  @Column(name = "CITY")
  private String city;

  @Column(name = "STATE")
  private String state;

  @Column(name = "ZIP_CODE")
  private String zipCode;

  @Column(name = "ADDRESS_TYPE")
  private String addressType;
}
