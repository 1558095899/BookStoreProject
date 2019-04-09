package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bookstore.service.OrderService;
import com.bookstore.user.Book;
import com.bookstore.user.OrderItem;
import com.bookstore.user.Orders;
import com.bookstore.user.User;

public class OrderServlet extends HttpServlet {
//订单生成
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据  并存入订单项
		Orders order=new Orders();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUser((User)request.getSession().getAttribute("user"));//把session对象中的用户信息保存到order对象中
			//获取购物车的域对象
			Map<Book,String> cart = (Map<Book,String>) request.getSession().getAttribute("cart");
			
			List<OrderItem> OrderItems=new ArrayList<OrderItem>();
			//遍历cart中的Book对象
			for (Book b : cart.keySet()) {
				OrderItem oi=new OrderItem();
				oi.setBook(b);//把Book对象封装到OrderItem中
				oi.setBuynum(Integer.parseInt(cart.get(b)));//将购买的商品数量封装到OrderItem中
				oi.setOrder(order);//将Order对象封装到OrderItem中
				OrderItems.add(oi);//将OrderItem存入list集合
			}
			//将集合封装到Orders对象中
			order.setOrderitem(OrderItems);
			//调用业务逻辑
			OrderService os=new OrderService();
			os.addOrder(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//分发转向   跳转到支付页面
		
		request.setAttribute("orderid",order.getId());
		request.setAttribute("money", order.getMoney());
		request.getRequestDispatcher("pay.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
