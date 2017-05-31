package com.packt.billing.client;

import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("bill")
public interface BillingService extends RemoteService {

	public Bill retrieve(String rommNumber);
	public boolean pay(String roomNumber, BigDecimal amount);
	
}
