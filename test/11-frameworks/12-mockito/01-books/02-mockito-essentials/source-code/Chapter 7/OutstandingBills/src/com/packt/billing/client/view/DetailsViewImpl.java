package com.packt.billing.client.view;

import java.math.BigDecimal;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.packt.billing.client.Bill;

public class DetailsViewImpl extends Composite implements DetailsView {
	private VerticalPanel mainPanel;
	private TextBox amount = new TextBox();
	private Button payment = new Button("Pay");
	private Button close = new Button("Close");
	private Label desc = new Label();
	private Label dueAmt = new Label();
	private Bill bill;
	
	public DetailsViewImpl() {
		mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		FlexTable mainTable = new FlexTable();
		mainTable.setWidth("100%");
		
		mainTable.setWidget(0, 0, new Label("Desc#"));
		mainTable.setWidget(0, 1, desc);
		
		mainTable.setWidget(1, 0, new Label("Due#"));
		mainTable.setWidget(1, 1, dueAmt);
		
		mainTable.setWidget(2, 0, new Label("Pay amount#"));
		mainTable.setWidget(2, 1, amount);
		
		mainTable.setWidget(3, 0, payment);
		mainTable.setWidget(3, 1, close);
		
		mainTable.getCellFormatter().setWidth(0, 0, "5%");
		mainTable.getCellFormatter().setWidth(0, 1, "60%");
		mainTable.getCellFormatter().setWidth(1, 0, "5%");
		mainTable.getCellFormatter().setWidth(1, 1, "60%");
		mainTable.getCellFormatter().setWidth(2, 0, "5%");
		mainTable.getCellFormatter().setWidth(2, 1, "60%");
		
		mainTable.getCellFormatter().setWidth(3, 0, "5%");
		mainTable.getCellFormatter().setWidth(3, 1, "60%");
		mainTable.getCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_MIDDLE);
		mainTable.getCellFormatter().setAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_MIDDLE);

		mainPanel.add(mainTable);
		initWidget(mainPanel);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getPaymentButton() {
		return payment;
	}

	@Override
	public HasValue<String> getPaymentAmount() {
		return amount;
	}

	@Override
	public void populate(Bill bill) {
		this.bill = bill;
		desc.setText(bill.getDetails());
		dueAmt.setText(""+bill.getPayable().doubleValue());
	}

	@Override
	public HasClickHandlers getCloseButton() {
		return close;
	}

	@Override
	public BigDecimal getOutstandingAmount() {
		return bill.getPayable();
	}

}
