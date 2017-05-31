package com.packt.mockito.advanced;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.packt.mockito.advanced.voidmethods.LDAPManager;
import com.packt.mockito.advanced.voidmethods.LoginController;

import static org.mockito.Mockito.*;
public class LoginControllerTest {
 
	LoginController controller;
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse response;
	@Mock LDAPManager ldapManager;
	@Mock HttpSession session;
	@Mock RequestDispatcher dispatcher;
	
	@Before
    public void beforeEveryTest(){
    	MockitoAnnotations.initMocks(this);
    	controller = new LoginController(ldapManager);
    }
	
	@Test
	public void when_valid_user_credentials_for_login_Then_routes_to_home_page() throws Exception{
		when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(true);
		when(request.getSession(true)).thenReturn(session);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(request.getParameter(anyString())).thenReturn("user","pwd");
		
		controller.process(request, response);
		
		verify(request).getSession(true);
		verify(session).setAttribute(anyString(), anyString());
		verify(request).getRequestDispatcher(eq("home.jsp"));
		verify(dispatcher).forward(request, response);
	}
	
	@Test
	public void when_invalid_user_credentials_Then_routes_to_login_page() throws Exception{
		when(ldapManager.isValidUser(anyString(), anyString())).thenReturn(false);
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		when(request.getParameter(anyString())).thenReturn("user","pwd");
		
		controller.process(request, response);
		
		verify(request).getRequestDispatcher(eq("login.jsp"));
		verify(dispatcher).forward(request, response);
	}
}
