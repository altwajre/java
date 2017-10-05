package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "bond")
public class Bond extends Investment{

  @Column(name = "VALUE")
  private BigDecimal value;

  @Column(name = "INTEREST_RATE")
  private BigDecimal interestRate;

  @Temporal(value = TemporalType.DATE)
  @Column(name = "MATURITY_DATE")
  private Date maturityDate;
}
