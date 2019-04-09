package com.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bookstore.UserException.UserException;
import com.bookstore.service.UserService;
import com.bookstore.user.User;

public class Modifyuserinfo extends HttpServlet {
//修改账户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//获取表单数据
		User user=new User();
		UserService us=new UserService();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			//调用业务逻辑
			us.modifyuserinfoById(user);
			//分发转向
			response.sendRedirect(request.getContextPath()+"/modifyUserInfoSuccess.jsp");
			//销毁用户
			request.getSession().invalidate();
		} catch (Exception e) {
			response.getWriter().write(e.getMessage());
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
