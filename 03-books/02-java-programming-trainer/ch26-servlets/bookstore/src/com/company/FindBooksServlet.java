package com.company;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/books", name="FindBooksServlet")
public class FindBooksServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String title = request.getParameter("booktitle");
		String author = request.getParameter("author");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><body>");
		out.println("<h2>the book " + title + " costs only $65; author by " + author);
		out.println("</body></html>");
	}
}
/*
 * If a form (or a script) submits multiple values, the URL includes several 
 * key/value pairs separated by the & symbol after the question mark:
 * 
 * go to http://localhost:8080/bookstore/books?booktitle=Java&author=Tom
 * 
 * output:
 * the book Java costs only $65; author by Tom
 */
