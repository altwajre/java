package com.packt.billing.client.event;

import java.math.BigDecimal;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.packt.billing.client.presenter.DetailsPresenter;
import com.packt.billing.client.presenter.Presenter;

public class PaymentButtonClickHandler implements ClickHandler {
	protected static final String PAYMENT_AMOUNT_CANNOT_EXCEED_THE_PAYABLE_AMOUNT = "Payment amount cannot exceed the payable amount";
	public static final String PLEASE_ENTER_A_POSITIVE_PAYMENT_AMOUNT = "Please enter a positive payment amount";
	public static final String PLEASE_ENTER_A_VALID_PAYMENT_AMOUNT = "Please enter a valid payment amount";
	public static final String PLEASE_ENTER_A_PAYMENT_AMOUNT = "Please enter a payment amount";
	private DetailsPresenter presenter;
	public PaymentButtonClickHandler(DetailsPresenter detailsPresenter) {
		this.presenter = detailsPresenter;
	}

	@Override
	public void onClick(final ClickEvent event) {
		    String amount = presenter.getDetailsView().getPaymentAmount().getValue();
			if(amount == null || "".equals(amount)){
				Window.alert(PLEASE_ENTER_A_PAYMENT_AMOUNT);
				return;
			}
			
			BigDecimal paymentAmt = null;
			try{
				double amtDbl = Double.parseDouble(amount);
				paymentAmt = new BigDecimal(amtDbl);
				
			}catch(NumberFormatException exception){
				Window.alert(PLEASE_ENTER_A_VALID_PAYMENT_AMOUNT);
				return;
			}
			
			if(paymentAmt.compareTo(BigDecimal.ZERO) <= 0){
				Window.alert(PLEASE_ENTER_A_POSITIVE_PAYMENT_AMOUNT);
				return;
			}
			
			if(presenter.getDetailsView().getOutstandingAmount().compareTo(paymentAmt) < 0){
				Window.alert(PAYMENT_AMOUNT_CANNOT_EXCEED_THE_PAYABLE_AMOUNT);
				return;
			}
			
			((Button)event.getSource()).setEnabled(false);
			presenter.makePayment(paymentAmt);
		
	}

}
