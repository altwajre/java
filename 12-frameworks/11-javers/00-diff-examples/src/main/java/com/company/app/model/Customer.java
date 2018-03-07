package com.company.app.model;

import lombok.Data;
import org.javers.core.metamodel.annotation.DiffIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Customer {
  @Id
  @GeneratedValue
  private Long id;
  @DiffIgnore
  private String name;
  private int age;
}
