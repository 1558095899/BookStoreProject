package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;
import com.bookstore.user.PageBean;

public class ShowProductByPage extends HttpServlet {
//商品类别
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//当前页
		int currentPage=1;
		//一页显示4条数据
		int pageSize=4;
		//根据类别显示商品
		
		
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		
		//获取当前页
		String currentPage1 = request.getParameter("currentPage");
		if(currentPage1!=null){
			currentPage=Integer.parseInt(currentPage1);
		}
		ProductsService ps=new ProductsService();
		PageBean pb=ps.ShowProductByPage(currentPage,pageSize,category);
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
