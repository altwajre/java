package com.packt.billing.server;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.packt.billing.client.Bill;
import com.packt.billing.client.BillingService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class BillingServiceImpl extends RemoteServiceServlet implements
		BillingService {

	private Map<String, BigDecimal> billMap = new HashMap<String, BigDecimal>();

	public BillingServiceImpl() {
		for (int i = 1; i < 5000; i++) {
			billMap.put("" + i, new BigDecimal(new Random().nextInt(5000)));
		}
	}

	@Override
	public Bill retrieve(String roomNumber) {
		BigDecimal payable = billMap.get(roomNumber);
		Bill bill = new Bill();
		if (payable != null) {
			bill.setDetails("Accomodation charge for room#" + roomNumber
					+ " and payable amount=" + payable.doubleValue());
		}

		bill.setPayable(payable);
		return bill;
	}

	@Override
	public boolean pay(String roomNumber, BigDecimal amount) {
		BigDecimal payable = billMap.get(roomNumber);
		if(payable != null){
			payable = payable.subtract(amount);
			billMap.put(roomNumber, payable);
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		new BillingServiceImpl();
		System.out.println("done");
	}
}
