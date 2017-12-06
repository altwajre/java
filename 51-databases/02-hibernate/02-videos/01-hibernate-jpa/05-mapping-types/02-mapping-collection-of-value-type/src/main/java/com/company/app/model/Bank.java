package com.company.app.model;

import lombok.Data;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "bank")
public class Bank {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "BANK_ID")
  private Long bankId;

  @Column(name = "NAME")
  private String name;

  @Embedded
  private Address address = new Address();

  @Column(name = "IS_INTERNATIONAL")
  private boolean international;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "LAST_UPDATED_DATE")
  private Date lastUpdatedDate;

  @Column(name = "LAST_UPDATED_BY")
  private String lastUpdatedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE")
  private Date createdDate;

  @Column(name = "CREATED_BY")
  private String createdBy;

  @ElementCollection
  @CollectionTable(name = "bank_contact", joinColumns = @JoinColumn(name = "BANK_ID"))
  @Column(name = "NAME")
  private Collection<String> contacts = new ArrayList<>();

  public String getAddressLine1() {
    return address.getAddressLine1();
  }

  public void setAddressLine1(String addressLine1) {
    this.address.setAddressLine1(addressLine1);
  }

  public String getAddressLine2() {
    return address.getAddressLine2();
  }

  public void setAddressLine2(String addressLine2) {
    this.address.setAddressLine2(addressLine2);
  }

  public String getCity() {
    return address.getCity();
  }

  public void setCity(String city) {
    this.address.setCity(city);
  }

  public String getState() {
    return address.getState();
  }

  public void setState(String state) {
    this.address.setState(state);
  }

  public String getZipCode() {
    return address.getZipCode();
  }

  public void setZipCode(String zipCode) {
    this.address.setZipCode(zipCode);
  }
}
