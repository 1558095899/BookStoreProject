package com.userapplication.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasiServlet extends HttpServlet {
	
	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		//强转
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		//获取请求参数
		String method = req.getParameter("method");
		try {
			//通过反射获取方法    *this为子类对象
			Method m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			//调用方法  传递参数
			m.invoke(this, req,req);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}
