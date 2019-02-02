package domain.discount;

import domain.model.Customer;
import domain.model.Money;

// Read from the database or file
public enum DiscountStore {
  INSTANCE;

  public double getPercentDiscount() {
    // Monday: 10%
    // Tuesday: 20%
    return 0.10; // return 10% for testing purpose
  }

  public double getCustomerPercentage(Customer customer) {
    // discount percentage should come from a database
    if(customer.getAge() > 60) {
      return 0.20;
    }

    return 0;
  }

  public Money getAbsoluteDiscountOverThreshold() {
    // return $2 for testing purpose
    return new Money(2);
  }

  public CompositePricingStrategy getSalePricingStrategy() {
    return new CompositeBestForCustomerPricingStrategy();
  }

}
