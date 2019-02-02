package domain.external.tax;

import domain.model.Money;
import domain.model.Sale;
import domain.model.TaxLineItem;

import java.util.List;

public class TaxManagerProvider {
  private Sale sale;

  public TaxManagerProvider(Sale sale) {
    this.sale = sale;
  }

  public List<TaxLineItem> getSalesTaxFromTaxManager() {
    Money total = new Money();
    Money subtotal = null;
    List<TaxLineItem> taxLineItems = sale.getTaxLineItems();
    for(TaxLineItem lineItem : taxLineItems) {
      subtotal = this.sale.getTotal().times(lineItem.getPercentage());
      total.add(subtotal);
    }
//    return total;
    return taxLineItems;
  }
}
