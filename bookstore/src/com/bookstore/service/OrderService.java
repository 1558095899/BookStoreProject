package com.bookstore.service;

import java.util.List;

import com.bookstore.UserException.UserException;
import com.bookstore.dao.BookDao;
import com.bookstore.dao.OrderDao;
import com.bookstore.dao.OrderItemDao;
import com.bookstore.until.AccountThreadLocal;
import com.bookstore.user.Orders;
//订单业务管理
public class OrderService {
	OrderDao orderDao=new OrderDao();
	OrderItemDao orderItemDao=new OrderItemDao();
	BookDao bookDao=new BookDao();
	public void addOrder(Orders order){
		try {
			//开启事务
			AccountThreadLocal.startTransaction();
			orderDao.insertOrder(order);//对订单数据库进行添加订单
			orderItemDao.insertOrder(order);//对订单项表数据库进行添加订单项
			bookDao.updatePnum(order);//对图书的库存数量进行修改
			//提交事务
			AccountThreadLocal.commit();
		} catch (Exception e) {
			//回滚事务
			AccountThreadLocal.rollback();
			e.printStackTrace();
		}
	}
	//通过用户UID查找对应订单
	public List<Orders> OrderFindByUser(int id) {
		List<Orders> order=null;
		try {
			order=orderDao.OrderFindByUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	//通过订单id查找订单信息
	public Orders OrderFindInfo(String orderid) {
		Orders order=null;
		try {
			order=orderDao.OrderFindInfoById(orderid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	//修改订单状态
	public void modifyOrderState(String orderid) throws UserException {
		try {
			orderDao.modifyOrderPayState(orderid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("交易失败！请联系客服");
		}
	}
	
}
