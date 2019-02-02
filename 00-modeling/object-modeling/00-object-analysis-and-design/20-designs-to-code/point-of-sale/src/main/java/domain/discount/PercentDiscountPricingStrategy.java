package domain.discount;

import domain.model.Money;
import domain.model.Sale;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy {
  private double percentage;

  public PercentDiscountPricingStrategy(double percentage) {
    this.percentage = percentage;
  }

  @Override
  public Money getTotal(Sale sale) {
    return sale.getPreDiscountTotal().times(percentage);
  }
}
