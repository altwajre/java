package com.company.app.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Investment {

  @Id
  @GeneratedValue(strategy=GenerationType.TABLE,generator="key_generator")
  @TableGenerator(table="ifinances_keys",pkColumnName="pk_name",
      valueColumnName="pk_value", name="key_generator")
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
