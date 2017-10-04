package com.company.app.model;

import com.company.app.model.id.CurrencyId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
@IdClass(CurrencyId.class)
public class Currency {

  @Id
  @Column(name="NAME")
  private String name;

  @Id
  @Column(name="COUNTRY_NAME")
  private String countryName;

  @Column(name="SYMBOL")
  private String symbol;
}
