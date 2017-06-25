package com.packt.billing.client.presenter;

import java.math.BigDecimal;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.packt.billing.client.Bill;
import com.packt.billing.client.BillingServiceAsync;
import com.packt.billing.client.event.PaymentButtonClickHandler;
import com.packt.billing.client.view.DetailsView;

public class DetailsPresenter implements Presenter {
	private final DetailsView detailsView;
	private final BillingServiceAsync billingService;
	private final String roomNumber;
	
	public DetailsPresenter(BillingServiceAsync service, DetailsView view, String rn) {
		this.detailsView = view;
		this.billingService = service;
		this.roomNumber = rn;
		
		detailsView.getCloseButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("START");
			}
		});
		
		detailsView.getPaymentButton().addClickHandler(new PaymentButtonClickHandler(this));
	}

	@Override
	public void render(final HasWidgets container) {
		container.clear();
		container.add(detailsView.asWidget());
		billingService.retrieve(roomNumber, new AsyncCallback<Bill>() {
			
			@Override
			public void onSuccess(Bill bill) {
				detailsView.populate(bill);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error occured "+caught);
			}
		});
	}

	public DetailsView getDetailsView() {
		return detailsView;
	}

	public void makePayment(BigDecimal amount){
		billingService.pay(roomNumber, amount, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error "+caught);
			}

			@Override
			public void onSuccess(Boolean result) {
				if(result){
					Window.alert("Posted payment");
					History.newItem("START");
				}else{
					Window.alert("Could not post payment");
				}
			}
		});
	}

}
