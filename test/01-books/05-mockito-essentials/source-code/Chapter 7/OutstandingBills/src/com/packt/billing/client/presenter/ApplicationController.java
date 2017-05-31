package com.packt.billing.client.presenter;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.packt.billing.client.BillingServiceAsync;
import com.packt.billing.client.event.SearchEvent;
import com.packt.billing.client.event.SearchEventHandler;
import com.packt.billing.client.view.DetailsViewImpl;
import com.packt.billing.client.view.QueryViewImpl;

public class ApplicationController implements Presenter, ValueChangeHandler<String>{

	private static final String SEARCH = "Search";
	private static final String BLANK = "";
	private static final String START = "START";
	private HasWidgets container;
	private HandlerManager eventBus;
	private String roomNumber;
	private BillingServiceAsync billingServiceAsync;
	public ApplicationController(BillingServiceAsync billingService){
		this.billingServiceAsync = billingService;
		eventBus = new HandlerManager(this);
		History.addValueChangeHandler(this);
		eventBus.addHandler(SearchEvent.TYPE, new SearchEventHandler() {
			
			@Override
			public void onSearch(SearchEvent event) {
				roomNumber = event.getRoomNumber();
				History.newItem(SEARCH);
			}
		});
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		container.clear();
		Presenter presenter = null;
		if (START.equals(token)) {
			presenter = new QueryPresenter(new QueryViewImpl(), eventBus);
		}

		if(SEARCH.equals(token)){
			presenter = new DetailsPresenter(billingServiceAsync, new DetailsViewImpl(), roomNumber);
		}
		if (presenter != null) {
			presenter.render(container);
		}
		
	}

	@Override
	public void render(HasWidgets container) {
		this.container = container;

		if (BLANK.equals(History.getToken())) {
			History.newItem(START);
		} else {
			History.fireCurrentHistoryState();
		}
		
	}

}
