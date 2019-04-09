package com.userapplication.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.bookstore.UserException.UserException;
import com.bookstore.service.UserService;
import com.bookstore.user.User;

//继承BasiServlet类    BasiServlet类继承HttpServlet
public class UserServlet extends BasiServlet {
	/**
	 * 将用户的所有功能整合到一个UserServlet中    进行管理
	 * 获取的参数值  要和UserServlet里的方法名相同
	 * 前端页面跳转时需写  如：<form action="${pageContext.request.contextPath }/user?method=login" method="post">
	 * 							   "${pageContext.request.contextPath}/user?method=register"
	 * 				<a	href="${pageContext.request.contextPath }/user?method=modifyuserinfo?id=${u.id}">用户信息修改</a>
	 * BasiServlet通过获取method来获取对应的方法名称    通过反射进行调用UserServlet里的方法
	 * 原先的servlet可以删除  并把web.xml里的对应配置删除干净
	 * 
	 * xml配置信息
	 * <servlet>
    	<servlet-name>BasiServlet</servlet-name>
    	<servlet-class>com.userapplication.servlet.BasiServlet</servlet-class>
  	  </servlet>

	 * 
	 * <servlet-mapping>
	    <servlet-name>BasiServlet</servlet-name>
	    <url-pattern>/user</url-pattern>
  	   </servlet-mapping>
	 */
	//登入
	public void login(HttpServletRequest request, HttpServletResponse response)
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
	//注册
	public void register(HttpServletRequest request, HttpServletResponse response)
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
	//修改用户信息
	public void modifyuserinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

}
