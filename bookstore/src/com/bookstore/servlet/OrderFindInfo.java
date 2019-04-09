package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.OrderService;
import com.bookstore.user.Orders;
//查看订单详情
public class OrderFindInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取order订单id
		String orderid = request.getParameter("orderid");
		OrderService or=new OrderService();
		//调用业务逻辑
		Orders order=or.OrderFindInfo(orderid);
		//分发转向
		request.setAttribute("order", order);
		request.getRequestDispatcher("orderInfo.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
