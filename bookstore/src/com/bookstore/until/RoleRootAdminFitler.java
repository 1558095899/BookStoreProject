package com.bookstore.until;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.user.User;
//后台管理权限设置
public class RoleRootAdminFitler implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//后台管理权限设置
		//强转
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		PrintWriter out = response.getWriter();
		//处理业务
		//获取用户     判断用户是否为null 且 是否为admin
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null){//用户不为null
			if(!"admin".equals(user.getRole())){//且不是管理员
				out.print("权限不足，无法进入！");
				//2秒后跳回首页
				response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
				//如果不是管理员   返回
				return;
			}
			//  如果是管理员  放行
			chain.doFilter(request, response);
		}else{
			//当用户为null  访问时  跳回登入
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		//分发转向
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	

}
