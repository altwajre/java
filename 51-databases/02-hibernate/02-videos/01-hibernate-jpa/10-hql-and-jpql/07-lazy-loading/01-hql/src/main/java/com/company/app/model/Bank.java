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
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
  @CollectionTable(name="bank_contact", joinColumns=@JoinColumn(name="BANK_ID"))
  @MapKeyColumn(name="POSITION_TYPE")
  @Column(name="NAME")
  private Map<String, String> contacts = new HashMap<String, String>();
}
