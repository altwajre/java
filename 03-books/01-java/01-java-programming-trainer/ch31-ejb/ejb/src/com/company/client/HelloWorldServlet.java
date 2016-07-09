package com.company.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.ejb.HelloWorldBean;

@WebServlet(urlPatterns = { "/HelloWorldServlet" })
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB HelloWorldBean myBean;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.println(myBean.sayHello());
	}
}
