package domain.discount;

import domain.model.Money;
import domain.model.Sale;

public interface ISalePricingStrategy {
  Money getTotal(Sale sale);
}
