package com.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dsna.util.images.ValidateCode;

public class ImageCode extends HttpServlet {
//验证码
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ValidateCode vc = new ValidateCode(110, 25, 4, 9);
		//向session中保存验证码
		request.getSession().setAttribute("scode", vc.getCode());
		vc.write(response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
