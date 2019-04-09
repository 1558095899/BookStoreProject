package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.user.User;

public class MyAccount extends HttpServlet {
//进入我的账户
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取session中保存的user数据  并判断是否登入
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){  //如果不为null  
			if("admin".equals(user.getRole())){
			//如果是管理员  进入后台
			request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
			return;
			}
			request.setAttribute("u",user);
			request.getRequestDispatcher("myAccount.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
