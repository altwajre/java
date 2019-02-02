package domain.model;

public class Payment {
  private Money amount;
  public Payment(Money amount) {
    this.amount = amount;
  }
  public Money getAmount() {
    return amount;
  }
}
