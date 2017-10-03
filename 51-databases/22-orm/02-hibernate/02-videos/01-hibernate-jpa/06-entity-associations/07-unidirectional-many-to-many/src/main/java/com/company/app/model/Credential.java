package com.company.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="credential")
public class Credential {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="CREDENTIAL_ID")
  public Long credentialId;

  @OneToOne(cascade= CascadeType.ALL)
  @JoinColumn(name="USER_ID")
  public User user;

  @Column(name="USERNAME")
  private String username;

  @Column(name="PASSWORD")
  private String password;
}
