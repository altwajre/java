package domain.external.tax;

public enum TaxStore {
  INSTANCE;

  public ITaxCalculatorAdapter getTaxAdapter() {
    // should read from file to create Adapter
    return new TaxManagerAdapter();
  }
}
