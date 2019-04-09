package com.bookservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.Book;
import com.bookservice.BookService;

public class FindProductByManyCondition extends HttpServlet {
//按条件查询
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookService bs=new BookService();
		//获取表单数据
		String id = request.getParameter("id");
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		//调用业务逻辑
		List<Book> books=bs.findProductByManyCondition(id,category,name,minprice,maxprice);
		//分发转向
		request.setAttribute("book", books);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
