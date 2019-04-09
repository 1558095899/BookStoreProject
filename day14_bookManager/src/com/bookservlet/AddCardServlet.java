package com.bookservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.Book;
import com.bookservice.BookService;

public class AddCardServlet extends HttpServlet {
//添加购物车
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		BookService bs=new BookService();
		Book b=bs.findBooksById(id);
		//获取session域对象
		HttpSession session = request.getSession();
		int num=1;
		//获取购物车
		Map<Book,String> cart = (Map<Book, String>) session.getAttribute("cart");
		//当购物车为空时  创建
		if(cart==null){
			cart=new HashMap<Book,String>();
			
		}
		//把获取的书信息 b  放入map集合   并存储到sessionu、域中
		if(cart.containsKey(b)){
			num=Integer.parseInt(cart.get(b))+1;
		}
		cart.put(b, num+"");
		session.setAttribute("cart", cart);
		out.println("加入购物车成功！！");
		out.println("<a href='"+request.getContextPath()+"/pageServlet'>继续购物</a>");
		out.println("<a href='"+request.getContextPath()+"/ad/cart.jsp'>查看购物车</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
