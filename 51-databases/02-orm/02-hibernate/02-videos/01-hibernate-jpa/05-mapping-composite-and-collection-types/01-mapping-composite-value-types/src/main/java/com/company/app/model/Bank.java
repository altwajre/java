package com.company.app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bank")
public class Bank {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="BANK_ID")
  private Long bankId;

  @Column(name="NAME")
  private String name;

//  @Embedded
//  private Address address = new Address();

  @Column(name="IS_INTERNATIONAL")
  private boolean international;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="LAST_UPDATED_DATE")
  private Date lastUpdatedDate;

  @Column(name="LAST_UPDATED_BY")
  private String lastUpdatedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="CREATED_DATE")
  private Date createdDate;

  @Column(name="CREATED_BY")
  private String createdBy;


}
