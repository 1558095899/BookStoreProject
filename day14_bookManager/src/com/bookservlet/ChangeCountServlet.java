package com.bookservlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.Book;

public class ChangeCountServlet extends HttpServlet {
//购物车改变数量
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String num=request.getParameter("num");
		String id=request.getParameter("id");
		HttpSession session = request.getSession();
		Map<Book,String> cart = (Map<Book, String>) session.getAttribute("cart");
		Book b=new Book();
		b.setId(id);
		if("0".equals(num)){
			cart.remove(b);
		}
		if(cart.containsKey(b)){
			cart.put(b, num);
		}
		response.sendRedirect(request.getContextPath()+"/ad/cart.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
