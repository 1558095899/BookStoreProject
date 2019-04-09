package com.bookstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;
import com.bookstore.user.Book;

public class FindAllServlet extends HttpServlet {
//后台显示所有图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据
		//调用业务逻辑
		ProductsService ps=new ProductsService();
		List<Book> book=ps.findAllBook();
		//分发转向
		request.setAttribute("book", book);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
