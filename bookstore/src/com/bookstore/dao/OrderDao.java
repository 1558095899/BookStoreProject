package com.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bookstore.until.AccountThreadLocal;
import com.bookstore.until.C3P0Util;
import com.bookstore.user.Book;
import com.bookstore.user.OrderItem;
import com.bookstore.user.Orders;

public class OrderDao {
/**
 * 添加订单
 * @param order
 * @throws Exception
 */
	public void insertOrder(Orders order) throws Exception {
		QueryRunner qr = new QueryRunner();
		qr.update(AccountThreadLocal.getConnection(),"insert into orders values(?,?,?,?,?,?,?,?)", order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverPhone(), order
						.getPaystate(), order.getOrdertime(), order.getUser().getId());
	}
	/**
	 * 通过用户id查找对应订单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Orders> OrderFindByUserById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from orders where user_id=?",new BeanListHandler<Orders>(Orders.class),id);
	
	}
	/**
	 * 根据订单id查询订单信息  并查询相应的订单项以及对应的图书信息
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public Orders OrderFindInfoById(String orderid) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDateSource());
		//查询订单信息
		Orders order = qr.query("SELECT * FROM orders WHERE id=?",new BeanHandler<Orders>(Orders.class),orderid);
		//根据当前订单的id 查询订单项和对应的图书信息
		List<OrderItem> orderItem = qr.query("SELECT * FROM orderitem o,book b WHERE o.product_id=b.id AND order_id=?",new ResultSetHandler<List<OrderItem>>(){

			@Override
			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				List<OrderItem> list=new ArrayList<OrderItem>();
				//手动封装数据进OrderItem和Book中
				while(rs.next()){
					//每条数据封装到一个新的OrderItem对象中
					OrderItem oi=new OrderItem();
					oi.setBuynum(rs.getInt("buynum"));//将购物的数量封装入OrderItem对象
					//没条数封装到Book对象中
					Book book=new Book();
					book.setName(rs.getString("name"));//将商品名称封装入Book对象
					book.setPrice(rs.getDouble("price"));//将商品价格封装入Book对象
					//将Book对象封装到OrderItem对象中
					oi.setBook(book);
					//将封装完成的OrderItem对象存入list集合
					list.add(oi);
				}
				return list;
			}
			
		},orderid);
		//将封装了数据的orderItem对象封装到Orders对象中
		order.setOrderitem(orderItem);
		return order;	
	}
	/**
	 * 修改订单支付状态
	 * @param orderid
	 * @throws Exception
	 */
	public void modifyOrderPayState(String orderid) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDateSource());
		qr.update("update orders set paystate=1 where id=?",orderid);
	}

}
