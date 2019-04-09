package com.bookservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.Book;
import com.bookservice.BookService;

public class LookServlet extends HttpServlet {
	//显示图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取表单数据
		//调用业务逻辑
	
		BookService book=new BookService();	
		List<Book> books=book.lookBookService();
		//分发转向

			request.setAttribute("book", books);
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
