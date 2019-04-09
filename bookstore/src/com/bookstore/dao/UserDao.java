package com.bookstore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bookstore.until.C3P0Util;
import com.bookstore.user.User;

public class UserDao {
	/**
	 * 注册用户信息
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("insert into user(username,password,gender,email,telephone,introduce,activeCode,state) values(?,?,?,?,?,?,?,?)",
				user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),user.getTelephone(),user.getIntroduce(),user.getActiveCode(),user.getState());
	}
	/**
	 * 通过激活码查找用户
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public User findUserByactiveCode(String activeCode) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		User query = qr.query("select * from user where activeCode=?", new BeanHandler<User>(User.class),activeCode);
		return query;
	}
	/**
	 * 改变用户状态
	 * @param activeCode
	 * @throws Exception
	 */
	public void updateActiveCode(String activeCode) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("update user set state=1 where activeCode=?",activeCode);
	}
	/**
	 * 通过用户名密码查询
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User findUserByNameAndPassword(String username, String password) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),username,password);
	}
	/**
	 * 通过id查找用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUserById(String id) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from user where id=?",new BeanHandler<User>(User.class),id);
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @throws Exception
	 */
	public void modifyuserinfo(User user) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("UPDATE USER SET PASSWORD=?,gender=?,telephone=? WHERE id=?;",user.getPassword(),user.getGender(),user.getTelephone(),user.getId());
	}
	

	
	
}
