package com.bookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.bookservice.BookService;

public class SearchAJAXInfoServlet extends HttpServlet {
//搜索框提示信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		BookService bs=new BookService();
		List<Object> s=bs.SearchAJAXInfoService(name);
		/*String str="";
		for(int i=0;i<s.size();i++){
			if(i>0){
				str+=",";
			}
			str+=s.get(i);
		}*/
		//JSON对象封装成数组形式      ajax就不需要切割
		String str=JSONArray.fromObject(s).toString();//["疯狂Java讲义","Java web","javaee","java"]
		out.print(str);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
