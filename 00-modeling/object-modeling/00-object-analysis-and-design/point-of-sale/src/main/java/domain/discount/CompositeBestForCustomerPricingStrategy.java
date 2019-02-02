package domain.discount;

import domain.model.Money;
import domain.model.Sale;

import java.util.Iterator;

public class CompositeBestForCustomerPricingStrategy extends CompositePricingStrategy {
  @Override
  public Money getTotal(Sale sale) {
    Money total = sale.getPreDiscountTotal();
    Money lowestTotal = new Money(Integer.MAX_VALUE);

    // iterate over all the inner strategies
    for(Iterator i = strategies.iterator(); i.hasNext();) {
      ISalePricingStrategy strategy = (ISalePricingStrategy) i.next();
      Money tempTotal = strategy.getTotal(sale);
      lowestTotal = tempTotal.min(lowestTotal);
    }

    total.minus(lowestTotal);

    return total;
  }
}
