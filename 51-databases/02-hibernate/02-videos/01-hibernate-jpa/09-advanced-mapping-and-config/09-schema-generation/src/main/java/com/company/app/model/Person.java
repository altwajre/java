package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {

  @Id
    /*
        Someone must generate identifier values; this annotation enables
        automatic generation of IDs.
     */
  @GeneratedValue
  private Long id;

  @Column(name = "Name")
  private String name;
}
