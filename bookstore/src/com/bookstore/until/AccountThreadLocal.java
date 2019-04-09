package com.bookstore.until;

import java.sql.Connection;
import java.sql.SQLException;

import com.bookstore.until.C3P0Util;

public class AccountThreadLocal {
	/**
	 * 
	 * ThreadLocal
		模拟ThreadLocal的设计，让大家明白他的作用。
		public class ThreadLocal{
		private Map<Runnable,Object> container = new HashMap<Runnable,Object>();
		public void set(Object value){
		container.put(Thread.currentThread(),value);//用当前线程作为key
		}
		public Object get(){
		return container.get(Thread.currentThread());
		}
		public void remove(){
		container.remove(Thread.currentThread());
		}
		}
		总结：调用该类的get方法，永远返回当前线程放入的数据。线程局部变量。

	 */
	private static ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	public static Connection getConnection(){
		Connection conn=tl.get();//获取当前线程
		if(conn==null){
			conn=C3P0Util.getConnection();
			tl.set(conn);
		}
		return conn;
	}
	//开启事务
	public static void startTransaction(){
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//提交事务
	public static void commit(){
		try {
			getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//回滚事务
	public static void rollback(){
		try {
			getConnection().rollback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//释放资源   并删除当前线程
	public static void close(){
		try {
			getConnection().close();
			tl.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//得到一个连接
	public static Connection getConnection(){
		Connection conn = tl.get();//从当前线程中取出一个连接
		if(conn==null){
			conn = C3P0Util.getConnection();//从池中取出一个
			tl.set(conn);//把conn对象放入到当前线程对象中
		}
		return conn;
	}*/
	
	/*//开始事务
	public static void startTransacation(){
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);//从当前线程对象中取出的连接，并开始事务
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(){
		try {
			getConnection().commit();//提交事务
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		try {
			getConnection().rollback();//回滚事务
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try {
			getConnection().close();//把连接放回池中
			tl.remove();//把当前线程对象中的conn移除
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
