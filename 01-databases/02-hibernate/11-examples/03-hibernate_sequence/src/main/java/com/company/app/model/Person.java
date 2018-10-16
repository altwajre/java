package com.company.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

  /*
      Every persistent entity class must have an identifier
      attribute annotated with <code>@Id</code>. Hibernate maps
      this attribute to a column named <code>ID</code>.
   */
  @Id
    /*
        Someone must generate identifier values; this annotation enables
        automatic generation of IDs.
     */
  @GeneratedValue
  private Long id;

  /*
      You usually implement regular attributes of a persistent class with private
      or protected fields, and public getter/setter method pairs. Hibernate maps
      this attribute to a column called <code>TEXT</code>.
   */
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
