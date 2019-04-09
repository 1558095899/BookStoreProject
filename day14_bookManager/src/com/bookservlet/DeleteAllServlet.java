package com.bookservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookservice.BookService;

public class DeleteAllServlet extends HttpServlet {
	// 批量删除
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs = new BookService();
		// 获取表单数据
		String[] id = request.getParameterValues("id");// 获取所有的value值
		// 调用业务逻辑
		bs.deleteAllBook(id);
		// 分发转向
		request.getRequestDispatcher("lookServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
