package com.bookstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;
import com.bookstore.user.Book;

public class EditBookNoUpload extends HttpServlet {
//无上传表单项    对图书进行修改
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = (Book) request.getAttribute("book");
		ProductsService ps=new ProductsService();
		ps.editBookNoUpload(book);
		request.getRequestDispatcher("findAllServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
