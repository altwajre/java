package domain.discount;

import domain.model.Customer;
import domain.model.Money;
import domain.model.Sale;

public class SeniorPricingStrategy implements ISalePricingStrategy {
  private Customer customer;
  private double percentage = 0.20;

  public SeniorPricingStrategy(Customer customer) {
    this.customer = customer;
  }

  @Override
  public Money getTotal(Sale sale) {
    if(customer.getAge() >= 60) {
      return sale.getTotal().times(percentage);
    }
    return sale.getTotal();
  }
}
