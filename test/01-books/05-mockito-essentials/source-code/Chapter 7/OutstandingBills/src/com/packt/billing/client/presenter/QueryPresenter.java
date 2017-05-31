package com.packt.billing.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.packt.billing.client.event.SearchEvent;
import com.packt.billing.client.view.QueryView;

public class QueryPresenter implements Presenter {
	private final QueryView queryView;
	private HandlerManager eventBus;

	public QueryPresenter(QueryView view,HandlerManager bus) {
		this.queryView = view;
		this.eventBus = bus;
		queryView.getQueryButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SearchEvent searchEvent = new SearchEvent();
				searchEvent.setRoomNumber(queryView.getRoomNumber().getValue());
				eventBus.fireEvent(searchEvent);
			}
		});
	}


	@Override
	public void render(HasWidgets container) {
		container.clear();
		container.add(queryView.asWidget());
	}

}
