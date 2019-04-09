package com.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;

public class DeleteBook extends HttpServlet {
//删除图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据
		String id = request.getParameter("id");
		//调用业务逻辑
		ProductsService ps=new ProductsService();
		ps.deleteBook(id);
		//分发转向
		request.getRequestDispatcher("findAllServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
