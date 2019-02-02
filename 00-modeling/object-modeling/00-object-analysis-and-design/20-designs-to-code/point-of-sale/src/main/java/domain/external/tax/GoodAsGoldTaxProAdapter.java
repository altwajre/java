package domain.external.tax;

import domain.model.Sale;
import domain.model.TaxLineItem;

import java.util.List;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter {
  @Override
  public List<TaxLineItem> getSalesTax(Sale sale) {
    GoodAsGoldTaxProProvider provider = new GoodAsGoldTaxProProvider(sale);
    return provider.getSalesTaxFromGoldTaxPro();
  }
}
