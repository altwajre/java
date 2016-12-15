package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shoppingcart")
public class ShoppingCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				Cookie currentCookie = cookies[i];
				String name = currentCookie.getName();
				String value = currentCookie.getValue();
				
				System.out.println("Received the cookie " + name + "=" + value);
			}
			
			// Get or create a session object
			HttpSession session = request.getSession();
			
			if(request.getParameter("placeorder") != null){
				session.invalidate();
			}
			
			// Try to get the shopping cart
			ArrayList<Book> myShoppingCart = (ArrayList<Book>)session.getAttribute("shoppingCart");
			
			if(myShoppingCart == null){
				// This is the first call - instantiate the shopping cart
				myShoppingCart = new ArrayList<>();
			}
			
			// Create an instance of a book object for received params
			Book selectedBook = new Book();
			selectedBook.title = request.getParameter("booktitle");
			selectedBook.price = Double.parseDouble(request.getParameter("price"));
			
			// Add the book to our shopping cart
			myShoppingCart.add(selectedBook);
			
			// Put the shopping cart back into the session object
			session.setAttribute("shoppingCart", myShoppingCart);
			
			// Prepare the Web page and sent it to the browser
			PrintWriter out = response.getWriter();
			
			// Add the content of the shopping cart to the web page
			out.println("<body>Your shopping cart content:");
			myShoppingCart.forEach(book -> {
				out.printf("<br>Title: %s, price: %.2f", book.title, book.price);
			});
			
			// Add the HTML form to the Web page
			out.println("<p>Add another book to the shopping cart: ");
			out.println("<form action=shoppingcart method=Get>");
		    out.println("<input type=Text name=booktitle>");
		    out.println("<input type=Text name=price>");
		    out.println("<input type=Submit value='Add to shopping cart'><br>");
		    out.println("<input type=Submit name=placeorder value='Place Order'>");
		    out.println("</form>");
		    out.println("</body>");			
		}
	}
}
/*
 * http://localhost:8080/bookstore/shoppingcart?booktitle=Java&price=18
 * 
 * output:
 * Your shopping cart content: 
 * Title: Java, price: 18.00
 * Add another book to the shopping cart:
 * Add to shopping cart
 * Place Order
 *
 */
