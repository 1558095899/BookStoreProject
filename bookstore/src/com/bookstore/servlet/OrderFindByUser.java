package com.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderService;
import com.bookstore.user.Orders;
import com.bookstore.user.User;

public class OrderFindByUser extends HttpServlet {
//通过用户查询订单
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取登入的用户
		User user = (User) request.getSession().getAttribute("user");
		//通过用户id查找对应的订单
		OrderService os=new OrderService();
		List<Orders> order=os.OrderFindByUser(user.getId());
		int size = order.size();
		
		//跳转到我的订单页面
		request.setAttribute("count", size);//将订单数量存入request域中
		request.setAttribute("u", user);//将user对象存入request域中
		request.setAttribute("order", order);//将订单存入request域中
		request.getRequestDispatcher("orderlist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
