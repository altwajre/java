package domain.external.tax;

import domain.model.Sale;
import domain.model.TaxLineItem;

import java.util.List;

public interface ITaxCalculatorAdapter {
  List<TaxLineItem> getSalesTax(Sale sale);
}
