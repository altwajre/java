package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "bank")
public class Bank {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="BANK_ID")
  private Long bankId;

  private String name;

  private Address address;
}
