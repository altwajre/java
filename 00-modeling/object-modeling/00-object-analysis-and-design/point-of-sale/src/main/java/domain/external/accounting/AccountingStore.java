package domain.external.accounting;

public enum AccountingStore {
  INSTANCE;

  public IAccoutingAdapter getAccoutingAdapter() {
    // should read from file to create Adapter
    return new SAPAccountingAdapter();
  }
}
