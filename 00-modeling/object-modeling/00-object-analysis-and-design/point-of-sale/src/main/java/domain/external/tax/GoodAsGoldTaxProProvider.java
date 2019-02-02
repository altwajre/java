package domain.external.tax;

import domain.model.Money;
import domain.model.Sale;
import domain.model.TaxLineItem;

import java.util.List;

public class GoodAsGoldTaxProProvider {
  private Sale sale;

  public GoodAsGoldTaxProProvider(Sale sale) {
    this.sale = sale;
  }

  public List<TaxLineItem> getSalesTaxFromGoldTaxPro() {
    Money total = new Money();
    Money subtotal = null;
    // Gold Tax
    List<TaxLineItem> lineItems = sale.getTaxLineItems();
    for(TaxLineItem lineItem : lineItems) {
      subtotal = this.sale.getTotal().times(lineItem.getPercentage());
      total.add(subtotal);
    }
//    return total;
    return lineItems;
  }

}
