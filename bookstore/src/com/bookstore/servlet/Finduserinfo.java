package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.UserException.UserException;
import com.bookstore.service.UserService;
import com.bookstore.user.User;

public class Finduserinfo extends HttpServlet {
//查找用户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		UserService us=new UserService();
		try {
			User user=us.findUserByIdInfo(id);
			
				request.setAttribute("u", user);
				request.getRequestDispatcher("modifyuserinfo.jsp").forward(request, response);
			
			
		} catch (UserException e) {
			//如果session失效   重新登入
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
