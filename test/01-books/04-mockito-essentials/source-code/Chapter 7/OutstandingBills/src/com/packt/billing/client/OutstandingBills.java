package com.packt.billing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.packt.billing.client.presenter.ApplicationController;
import com.packt.billing.client.presenter.Presenter;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class OutstandingBills implements EntryPoint {

	private final BillingServiceAsync service = GWT.create(BillingService.class);

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		Presenter presenter = new ApplicationController(service);
		presenter.render(RootPanel.get("dom"));
	}
}
