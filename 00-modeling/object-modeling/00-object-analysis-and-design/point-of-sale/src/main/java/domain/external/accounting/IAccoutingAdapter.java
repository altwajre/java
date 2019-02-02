package domain.external.accounting;

import domain.model.Payment;
import domain.model.Sale;

public interface IAccoutingAdapter {
  void postReceivable(Payment creditPayent);
  void postSale(Sale sale);
}
