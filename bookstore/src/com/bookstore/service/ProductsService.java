package com.bookstore.service;

import java.util.List;

import com.bookstore.dao.BookDao;
import com.bookstore.user.Book;
import com.bookstore.user.PageBean;

public class ProductsService {
	//查询所有图书信息
	BookDao bd=new BookDao();
	public List<Book> findAllBook() {
		List<Book> list=null;
		try {
			list=bd.findAllBookInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//添加图书
	public void addBook(Book book) {
		try {
			bd.addBookInfo(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据id查找图书
	public Book findBookById(String id) {
		Book book=null;
		try {
			book=bd.findBookByIdInfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	//修改图书信息
	public void editBook(Book book) {
		try {
			bd.editBookInfo(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除图书
	public void deleteBook(String id) {
		try {
			bd.deleteBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//修改图书信息  （无上传表单）
	public void editBookNoUpload(Book book) {
		try {
			bd.editBookNoUp(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//按条件查询
	public List<Book> FindProductByCondition(String id, String category,
			String name, String minprice, String maxprice) {
		List<Book> list=null;
		try {
			list=bd.FindProductByManyCondition(id,category,name,minprice,maxprice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//分页
	public PageBean ShowProductByPage(int currentPage, int pageSize, String category) {
		try {
			//获取book条数
			int count=bd.getProductCount(category);
			//计算所有页数
			int totalPage=(int) Math.ceil(count*1.0/pageSize);
			//获取分页后的数据
			List<Book> book=bd.getProductByPage(currentPage,pageSize,category);
			
			PageBean pb=new PageBean();
			pb.setCount(count);
			pb.setTotalPage(totalPage);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setBook(book);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
