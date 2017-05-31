package com.packt.billing.client;

import java.io.Serializable;
import java.math.BigDecimal;

public class Bill implements Serializable {

	private static final long serialVersionUID = 1L;
	private String details;
	private BigDecimal payable;

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public BigDecimal getPayable() {
		return payable;
	}

	public void setPayable(BigDecimal payable) {
		this.payable = payable;
	}

}
