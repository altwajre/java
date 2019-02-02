package domain.discount;

import domain.model.Money;
import domain.model.Sale;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositePricingStrategy implements ISalePricingStrategy {
  protected List<ISalePricingStrategy> strategies = new ArrayList<>();

  public void add(ISalePricingStrategy discountPricingStrategy) {
    strategies.add(discountPricingStrategy);
  }

  @Override
  public abstract Money getTotal(Sale sale);
}
