package com.packt.billing.client.event;

import static com.packt.billing.client.event.PaymentButtonClickHandler.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.junit.GWTMockUtilities;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasValue;
import com.packt.billing.client.presenter.DetailsPresenter;
import com.packt.billing.client.view.DetailsView;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Window.class)
public class PaymentButtonClickHandlerTest {

	PaymentButtonClickHandler handler;
	@Mock
	DetailsPresenter mockPresenter;
	ArgumentCaptor<String> captor = null;
	@Mock
	ClickEvent clickEvent;
	@Mock
	DetailsView detailsView;
	@Mock
	HasValue<String> payAmount;
	@Before
	public void before() throws Exception{
		GWTMockUtilities.disarm();
		when(mockPresenter.getDetailsView()).thenReturn(detailsView);
		when(detailsView.getPaymentAmount()).thenReturn(payAmount);
		handler = new PaymentButtonClickHandler(mockPresenter);
		mockStatic(Window.class);
		captor = ArgumentCaptor.forClass(String.class);
		doNothing().when(Window.class, "alert", captor.capture());
	}
	
	@After
	public void after(){
		GWTMockUtilities.restore();
	}
	
	@Test
	public void sanity() throws Exception {
		Window.alert("dd");
		assertEquals("dd",captor.getValue());
	}
	
	@Test
	public void when_empty_payment_amount_then_raises_error() throws Exception {
		handler.onClick(clickEvent);
		assertEquals(PLEASE_ENTER_A_PAYMENT_AMOUNT,captor.getValue());
	}
	
	@Test
	public void when_invalid_payment_amount_then_raises_error() throws Exception {
		when(payAmount.getValue()).thenReturn("abc$$$");
		handler.onClick(clickEvent);
		assertEquals(PLEASE_ENTER_A_VALID_PAYMENT_AMOUNT,captor.getValue());
	}
	
	@Test
	public void when_zero_payment_amount_then_raises_error() throws Exception {
		when(payAmount.getValue()).thenReturn("0.00");
		handler.onClick(clickEvent);
		assertEquals(PLEASE_ENTER_A_POSITIVE_PAYMENT_AMOUNT,captor.getValue());
	}
	
	@Test
	public void when_negative_payment_amount_then_raises_error() throws Exception {
		when(payAmount.getValue()).thenReturn("-10.00");
		handler.onClick(clickEvent);
		assertEquals(PLEASE_ENTER_A_POSITIVE_PAYMENT_AMOUNT,captor.getValue());
	}
	
	@Test
	public void when_payment_amount_exceeds_the_payable_then_raises_error() throws Exception {
		when(payAmount.getValue()).thenReturn("100.00");
		when(detailsView.getOutstandingAmount()).thenReturn(new BigDecimal("50.00"));
		handler.onClick(clickEvent);
		assertEquals(PAYMENT_AMOUNT_CANNOT_EXCEED_THE_PAYABLE_AMOUNT,captor.getValue());
	}
	
	@Test
	public void when_payment_amount_not_greater_than_payable_amount_then_posts_the_payment() throws Exception {
		Button pay = PowerMockito.mock(Button.class);
		PowerMockito.when(clickEvent.getSource()).thenReturn(pay);
		when(payAmount.getValue()).thenReturn("100.00");
		when(detailsView.getOutstandingAmount()).thenReturn(new BigDecimal("200.00"));
		handler.onClick(clickEvent);
		verifyStatic(Mockito.never());
	}
}

