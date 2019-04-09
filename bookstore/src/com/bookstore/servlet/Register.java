package com.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;



import com.bookstore.UserException.UserException;
import com.bookstore.service.UserService;
import com.bookstore.user.User;

public class Register extends HttpServlet {
//注册用户
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//效验验证码
		String ckcode=request.getParameter("ckcode");
		
		Object scode = request.getSession().getAttribute("scode");
		if(!scode.equals(ckcode.toUpperCase())){
			out.println("验证码错误！");
			return;
		}
		//获取表单数据
		User user=new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setActiveCode(UUID.randomUUID().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用业务逻辑
		UserService us=new UserService();
		try {
			us.register(user);
			//分发转向
			request.setAttribute("user", user);
			request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
		} 
		catch (UserException e) {
			request.setAttribute("msg_error", e.getMessage());
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
