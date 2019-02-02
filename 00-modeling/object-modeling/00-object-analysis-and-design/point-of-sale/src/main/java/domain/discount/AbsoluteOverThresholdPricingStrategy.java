package domain.discount;

import domain.model.Money;
import domain.model.Sale;

public class AbsoluteOverThresholdPricingStrategy implements ISalePricingStrategy {
  private Money discount;
  private Money threshold = new Money(20);

  public AbsoluteOverThresholdPricingStrategy(Money discount) {
    if(discount.getAmount().compareTo(threshold.getAmount()) == 1) {
      throw new RuntimeException("discount can not greater than threshold");
    }

    this.discount = discount;
  }

  @Override
  public Money getTotal(Sale sale) {
    Money pdt = sale.getPreDiscountTotal();

    pdt.minus(this.discount);
    return pdt;
  }
}
