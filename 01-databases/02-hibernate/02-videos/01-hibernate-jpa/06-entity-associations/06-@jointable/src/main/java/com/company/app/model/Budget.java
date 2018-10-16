package com.company.app.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "budget")
public class Budget {

  @Id
  @GeneratedValue
  @Column(name = "BUDGET_ID")
  private Long budgetId;

  @Column(name = "NAME")
  private String name;

  @Column(name = "GOAL_AMOUNT")
  private BigDecimal goalAmount;

  @Column(name = "PERIOD")
  private String period;

  @OneToMany(cascade=CascadeType.ALL)
  @JoinTable(name="budget_transaction",
      joinColumns=@JoinColumn(name="BUDGET_ID"), // Owning entity (budget) - BUDGET_ID = budget.BUDGET_ID
      inverseJoinColumns=@JoinColumn(name="TRANSACTION_ID")) // TRANSACTION_ID = transaction.TRANSACTION_ID
  private List<Transaction> transactions = new ArrayList<>();

}
