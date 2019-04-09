package com.bookservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.Book;
import com.bookservice.BookService;

public class GetBookinfoServlet extends HttpServlet {
//查看图书详情
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookService bs=new BookService();
		//获取表单信息
		String id=request.getParameter("id");
		//调用业务逻辑
		Book books = bs.findBooksById(id);
		//分发转向
		request.setAttribute("book", books);
		request.getRequestDispatcher("/ad/product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
