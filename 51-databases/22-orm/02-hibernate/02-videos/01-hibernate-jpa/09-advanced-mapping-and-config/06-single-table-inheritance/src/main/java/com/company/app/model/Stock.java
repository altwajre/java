package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@DiscriminatorValue("ST")
public class Stock extends Investment {

  @Column(name = "SHARE_PRICE")
  private BigDecimal sharePrice;

  @Column(name = "QUANTITY")
  private BigDecimal quantity;
}
