package domain.model;

import java.math.BigDecimal;

public class TaxLineItem {
  private String description;
  private double percentage;
  private BigDecimal amount;

  public TaxLineItem(String description, double percentage) {
    this.description = description;
    this.percentage = percentage;
  }

  public String getDescription() {
    return this.description;
  }

  public double getPercentage() {
    return this.percentage;
  }
}
