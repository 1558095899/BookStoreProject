package com.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ProductsService;
import com.bookstore.user.Book;

public class AddCart extends HttpServlet {
//添加到购物车
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取表单数据
		String id = request.getParameter("id");
		//调用业务逻辑
		int num=1;
		ProductsService ps=new ProductsService();
		Book b = ps.findBookById(id);
		//获取cart
		Map<Book,String> cart = (Map<Book,String>)request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new HashMap();
		}
		if(cart.containsKey(b)){
			num=Integer.parseInt(cart.get(b))+1;//通过key获取value值并加1
		}
		cart.put(b,num+"");
		//把cart放入session域中
		request.getSession().setAttribute("cart", cart);
		//分发转向
		/*PrintWriter out = response.getWriter();
		out.println("加入购物车成功！！");
		out.println("<a href='"+request.getContextPath()+"/showProductByPage'>继续购买</a>");
		out.println("<a href='"+request.getContextPath()+"/cart.jsp'>查看购物车</a>");*/
		//加入购物车成功后跳回商品详情页面   即原来的页面
		request.getRequestDispatcher("product_info.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
