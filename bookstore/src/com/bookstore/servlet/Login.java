package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.UserException.UserException;
import com.bookstore.service.UserService;
import com.bookstore.user.User;

public class Login extends HttpServlet {
//登入
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkbox = request.getParameter("checkbox");
		//调用业务逻辑
		UserService us=new UserService();
		//记住用户名
		Cookie cookie = new Cookie("username", username);
		cookie.setPath("/");
		try {
			User u=us.login(username,password);
			//判断用户是否为空
			if(u!=null){
				//判断状态是否为空
				if(checkbox!=null){
					//设置最大存活时间
					cookie.setMaxAge(Integer.MAX_VALUE);
				}else{
					//删除会话
					cookie.setMaxAge(0);
				}
				//响应回客户端
				response.addCookie(cookie);
				
			}
		//分发转向
			//判断普通用户和管理员
			request.getSession().setAttribute("user", u);
			
			//跳到首页
			request.getRequestDispatcher("index.jsp").forward(request, response);;
		} catch (UserException e) {
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
