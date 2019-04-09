package com.bookstore.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.user.Book;

public class ChangCount extends HttpServlet {
//改变购物车数量
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		Map<Book,String> cart = (Map<Book,String>)session.getAttribute("cart");
		Book b=new Book();
		b.setId(id);
		if("0".equals(num)){
			cart.remove(b);
		}
		if(cart.containsKey(b)){
			cart.put(b, num);
		}
		request.getRequestDispatcher("cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
