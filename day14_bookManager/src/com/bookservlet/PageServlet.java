package com.bookservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.PageBean;
import com.bookservice.BookService;

public class PageServlet extends HttpServlet {
//分页功能
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageSize=4;//一页显示4个
		int currentPage=1;//当前页
		//获取表单数据
		String currentPage1=request.getParameter("currentPage");
		if(currentPage1!=null){
			currentPage=Integer.parseInt(currentPage1);//把获得的数据转成int类型并赋值给currentPage
		}
		//调用业务逻辑
		BookService bs=new BookService();
		PageBean pb=bs.pageService(currentPage,pageSize);
		request.setAttribute("pb", pb);
		//分发转向
		request.getRequestDispatcher("/ad/product_list.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
