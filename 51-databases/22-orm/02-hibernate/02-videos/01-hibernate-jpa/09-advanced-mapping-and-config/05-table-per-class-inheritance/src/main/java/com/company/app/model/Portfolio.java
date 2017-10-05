package com.company.app.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "portfolio")
public class Portfolio {

  @Id
  @GeneratedValue
  @Column(name="PORTFOLIO_ID")
  private Long portfolioId;

  @Column(name="NAME")
  private String name;

  @OneToMany(mappedBy="portfolio")
  private List<Investment> investements = new ArrayList<Investment>();
}
