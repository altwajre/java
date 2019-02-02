package domain.external.tax;

import domain.model.Sale;
import domain.model.TaxLineItem;

import java.util.List;

public class TaxManagerAdapter implements ITaxCalculatorAdapter {
  @Override
  public List<TaxLineItem> getSalesTax(Sale sale) {
    TaxManagerProvider provider = new TaxManagerProvider(sale);
    return provider.getSalesTaxFromTaxManager();
  }
}
