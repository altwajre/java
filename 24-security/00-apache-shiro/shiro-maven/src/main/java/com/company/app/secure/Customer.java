package com.company.app.secure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class Customer extends AbstractModel {

  private String firstName;
  private String lastName;

  @Masked
  private String pin;

  @Masked
  private String ssn;

  @JsonIgnore
  private boolean alive;

  @Masked
  @JsonView(Views.Secure.class)
  private String email;

}
