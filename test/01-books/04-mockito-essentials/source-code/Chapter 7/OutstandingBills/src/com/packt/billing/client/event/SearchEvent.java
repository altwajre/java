package com.packt.billing.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SearchEvent extends GwtEvent<SearchEventHandler> {
	private String roomNumber;

	public static Type<SearchEventHandler> TYPE = new Type<SearchEventHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SearchEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SearchEventHandler handler) {
		handler.onSearch(this);
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
