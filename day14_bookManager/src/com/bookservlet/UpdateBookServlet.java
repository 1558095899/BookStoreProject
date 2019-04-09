package com.bookservlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.book.Book;
import com.bookservice.BookService;

public class UpdateBookServlet extends HttpServlet {
	//更新图书信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BookService bs=new BookService();
		Book book=new Book();
		//获取表单数据
		try {
			BeanUtils.populate(book, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用业务逻辑
		bs.updateBooks(book);
		//分发转向
		request.getRequestDispatcher("lookServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
