package com.packt.billing.client.view;

import java.math.BigDecimal;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.packt.billing.client.Bill;

public interface DetailsView {
	Widget asWidget();
	HasClickHandlers getPaymentButton();
	HasClickHandlers getCloseButton();
	HasValue<String> getPaymentAmount();
	BigDecimal getOutstandingAmount();
	void populate(Bill bill);
}
