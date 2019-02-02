package domain.external;

import domain.external.accounting.AccountingStore;
import domain.external.accounting.IAccoutingAdapter;
import domain.external.tax.GoodAsGoldTaxProAdapter;
import domain.external.tax.ITaxCalculatorAdapter;
import domain.external.tax.TaxManagerAdapter;
import domain.external.tax.TaxStore;

//
/*
Singleton Factory
Singleton pattern
Factory pattern
Adapters generated from a Singleton Factory

 */
public enum ServiceFactory {
  INSTANCE;

  public IAccoutingAdapter getAccountingAdapter() {
    AccountingStore accountingStore = AccountingStore.INSTANCE;
    IAccoutingAdapter accoutingAdapter = accountingStore.getAccoutingAdapter();
    return accoutingAdapter;
  }

  public ITaxCalculatorAdapter getTaxCalculatorAdapter() {
    TaxStore taxStore = TaxStore.INSTANCE;
    ITaxCalculatorAdapter taxAdapter = taxStore.getTaxAdapter();
    return taxAdapter;
  }
}
