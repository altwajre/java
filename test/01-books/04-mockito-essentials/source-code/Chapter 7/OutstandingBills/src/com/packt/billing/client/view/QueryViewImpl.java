package com.packt.billing.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QueryViewImpl extends Composite  implements QueryView {
	private HorizontalPanel mainPanel;
	private TextBox roomNumber= new TextBox();
	private Button query = new Button("Query");

	public QueryViewImpl(){
		mainPanel = new HorizontalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		FlexTable mainTable = new FlexTable();
		mainTable.setWidth("100%");
		mainTable.setWidget(0, 0, new Label("Room#"));
		mainTable.setWidget(0, 1, roomNumber );
		mainTable.setWidget(0, 2, query );
		mainTable.getCellFormatter().setWidth(0, 0, "5%");
		mainTable.getCellFormatter().setWidth(0, 1, "10%");
		
		mainPanel.add(mainTable);
		
		initWidget(mainPanel);
	}
	
	@Override public Widget asWidget() {
		return this;
	}

	@Override public HasClickHandlers getQueryButton() {
		return query;
	}

	@Override public HasValue<String> getRoomNumber() {
		return roomNumber;
	}

}
