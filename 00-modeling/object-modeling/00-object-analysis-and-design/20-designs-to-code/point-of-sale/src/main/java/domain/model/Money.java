package domain.model;

import java.math.BigDecimal;

public class Money {
  private BigDecimal amount;

  public Money() {
    this(new BigDecimal(0));
  }

  public Money(BigDecimal amount) {
    this.amount = amount;
  }

  public Money(double amount) {
    this.amount = new BigDecimal(amount);
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Money times(int quantity) {
    BigDecimal factor = new BigDecimal(quantity);
    BigDecimal newAmount = amount.multiply(factor);
    return new Money(newAmount);
  }

  public Money times(double percentage) {
    BigDecimal factor = new BigDecimal(percentage);
    BigDecimal newAmount = amount.multiply(factor);
    return new Money(newAmount);
  }

  public void minus(Money money) {
    amount = amount.subtract(money.getAmount());
  }

  public void add(Money subtotal) {
    amount = amount.add(subtotal.getAmount());
  }

  public Money min(Money lowestTotal) {
    BigDecimal min = amount.min(lowestTotal.amount);
    return new Money(min);
  }

  @Override
  public String toString() {
    return String.format("%.2f", amount);
  }
}
