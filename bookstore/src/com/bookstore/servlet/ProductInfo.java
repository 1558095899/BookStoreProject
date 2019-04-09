package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;
import com.bookstore.user.Book;

public class ProductInfo extends HttpServlet {
//查看商品详情
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据
		String id = request.getParameter("id");
		//调用业务逻辑
		ProductsService ps=new ProductsService();
		Book book=ps.findBookById(id);
		//分发转向
		request.getSession().setAttribute("book", book);
		request.getRequestDispatcher("product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
