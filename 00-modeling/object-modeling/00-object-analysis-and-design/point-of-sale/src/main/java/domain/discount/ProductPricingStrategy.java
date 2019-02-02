package domain.discount;

import domain.model.*;

import java.util.List;

public class ProductPricingStrategy implements ISalePricingStrategy {
  private double darjeelingPercentage = 0.10;

  @Override
  public Money getTotal(Sale sale) {
    List<SalesLineItem> salesLineItems = sale.getSalesLineItems();

    for(SalesLineItem lineItem : salesLineItems) {
      ProductDescription productDescription = lineItem.getProduct();
      if(productDescription.getDescription().toLowerCase().contains("darjeeling tea")) {
        return sale.getTotal().times(darjeelingPercentage);
      }
    }

    return sale.getTotal();
  }
}
