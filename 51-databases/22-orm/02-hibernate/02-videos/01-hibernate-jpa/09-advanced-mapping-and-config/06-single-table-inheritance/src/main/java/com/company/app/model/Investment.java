package com.company.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INVESTMENT_TYPE")
@Table(name="investment")
public abstract class Investment {

  @Id
  @GeneratedValue
  @Column(name="INVESTMENT_ID")
  private Long investmentId;

  @JoinColumn(name="PORTFOLIO_ID")
  @ManyToOne(cascade=CascadeType.ALL)
  private Portfolio portfolio;

  @Column(name = "NAME")
  protected String name;

  @Column(name = "ISSUER")
  protected String issuer;

  @Column(name = "PURCHASE_DATE")
  protected Date purchaseDate;
}
