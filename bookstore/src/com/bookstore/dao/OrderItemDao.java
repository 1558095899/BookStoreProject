package com.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.bookstore.until.AccountThreadLocal;
import com.bookstore.until.C3P0Util;
import com.bookstore.user.OrderItem;
import com.bookstore.user.Orders;

public class OrderItemDao {
/**
 * 添加订单项表信息
 * @param order
 * @throws Exception
 */
	public void insertOrder(Orders order) throws Exception {
		QueryRunner qr=new QueryRunner();
		List<OrderItem> orderitem = order.getOrderitem();
		Object[][] params=new Object[orderitem.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i]=new Object[]{order.getId(),orderitem.get(i).getBook().getId(),orderitem.get(i).getBuynum()};
		}
		qr.batch(AccountThreadLocal.getConnection(),"insert into orderitem values(?,?,?)", params);
	}
	
}
