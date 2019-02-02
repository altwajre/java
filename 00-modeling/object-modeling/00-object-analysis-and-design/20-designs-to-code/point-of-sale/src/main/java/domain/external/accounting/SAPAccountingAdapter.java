package domain.external.accounting;

import domain.model.Payment;
import domain.model.Sale;

public class SAPAccountingAdapter implements IAccoutingAdapter {
  @Override
  public void postReceivable(Payment creditPayent) {

  }

  @Override
  public void postSale(Sale sale) {

  }
}
