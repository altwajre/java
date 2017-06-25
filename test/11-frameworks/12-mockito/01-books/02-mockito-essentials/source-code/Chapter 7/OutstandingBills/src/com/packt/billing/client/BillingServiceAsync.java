package com.packt.billing.client;

import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BillingServiceAsync {
	public void retrieve(String rommNumber, AsyncCallback<Bill> callback);
	public void pay(String roomNumber, BigDecimal amount, AsyncCallback<Boolean> callback);
}
