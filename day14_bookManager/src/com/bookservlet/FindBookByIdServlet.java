package com.bookservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.Book;
import com.bookservice.BookService;

public class FindBookByIdServlet extends HttpServlet {
	//根据id查找图书
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		BookService bs=new BookService();
		//获取表单数据
		String id = request.getParameter("id");
		//调用业务逻辑
		Book books=bs.findBooksById(id);
		//分发转向
		if(books!=null){
			request.setAttribute("book", books);
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
