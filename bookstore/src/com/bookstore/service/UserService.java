package com.bookstore.service;

import java.util.List;

import com.bookstore.UserException.UserException;
import com.bookstore.dao.UserDao;
import com.bookstore.until.SendJMail;
import com.bookstore.user.User;

public class UserService {
	UserDao ud=new UserDao();
	//注册用户
	public void register(User user) throws UserException {
		try {
			//注册用户
			ud.addUser(user);
			/*//给用户邮箱发送激活码
			String emailMsg="注册成功，请<a href='http://www.product.com?activeCode=ffddff14'>激活</a>后登录";
			SendJMail.sendMail(user.getEmail(), emailMsg);*/
			
		} catch (Exception e) {
			throw new UserException("注册失败！");
		}
	}
	//激活码验证激活
	public User activeCode(String activeCode) {
		User u=null;
		try {
			u=ud.findUserByactiveCode(activeCode);
			if(u!=null){
				ud.updateActiveCode(activeCode);
			}
			throw new UserException("激活失败");
		} catch (Exception e) {
				// TODO
		}
		return u;
	}
	//登入验证
	public User login(String username, String password) throws UserException {
		User u=null;
		try {
			u=ud.findUserByNameAndPassword(username,password);
			if(u==null){
				throw new UserException("用户名或密码错误！");
			}
		} catch (Exception e) {
			throw new UserException("用户名或密码错误！");
		}
		return u;
	}
	//通过id查找用户
	public User findUserByIdInfo(String id) throws UserException {
		try {
			return ud.findUserById(id);
		} catch (Exception e) {
			throw new UserException("用户查找失败");
		}
		
	}
	//修改用户信息
	public void modifyuserinfoById(User user) throws UserException {
		try {
			ud.modifyuserinfo(user);
		} catch (Exception e) {
			throw new UserException("修改用户信息失败");
		}
	}
}

