package domain.discount;

import domain.model.Customer;
import domain.model.Sale;

public enum PricingStrategyFactory {
  INSTANCE;
  private DiscountStore discountStore = DiscountStore.INSTANCE;
  private Sale sale;
  private CompositePricingStrategy compositePricingStrategy;

  public void addCustomerPricingStrategy(Sale sale) {
    this.sale = sale;
    Customer customer = sale.getCustomer();
    compositePricingStrategy = sale.getPricingStrategy();

    // Get Customer (senior) discount: 20% off
    double pct = getCustomerPercentage(customer);
    ISalePricingStrategy percentDiscountPricingStrategy = new PercentDiscountPricingStrategy(pct);
    compositePricingStrategy.add(percentDiscountPricingStrategy);
  }

  public double getCustomerPercentage(Customer customer) {
    return discountStore.getCustomerPercentage(customer);
  }

  public ISalePricingStrategy getSalePricingStrategy() {
    if(compositePricingStrategy == null) {
      compositePricingStrategy = discountStore.getSalePricingStrategy();
    }

    // Get Store discount: 10% off all sales read from a data store
    // Figure 26.17. Creating a composite strategy
    double storeDiscountPercent = discountStore.getPercentDiscount();
    ISalePricingStrategy percentDiscountPricingStrategy = new PercentDiscountPricingStrategy(storeDiscountPercent);
    compositePricingStrategy.add(percentDiscountPricingStrategy);

    return compositePricingStrategy;
  }

  public ISalePricingStrategy getSeniorPricingStrategy() {
    return new SeniorPricingStrategy(sale.getCustomer());
  }

}
