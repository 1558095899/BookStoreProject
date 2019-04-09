package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UserService;
import com.bookstore.user.User;

public class ActiveCode extends HttpServlet {
//激活邮箱
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String activeCode=request.getParameter("name");
		
		UserService us=new UserService();
		User u=us.activeCode(activeCode);
		if(u!=null){
			response.getWriter().write("激活成功！！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
