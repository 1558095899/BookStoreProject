package com.bookservice;

import java.util.List;

import com.book.Book;
import com.book.PageBean;
import com.bookdao.BookDao;

public class BookService {
	BookDao dao=new BookDao();
	//处理显示图书
	public List<Book> lookBookService(){
		try {
			return dao.lookBook();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//处理添加图书
	public void addBooks(Book book) {
		try {
			dao.addBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//查找图书
	public Book findBooksById(String id) {
		try {
			return dao.findBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	//修改图书信息
	public void updateBooks(Book book) {
		try {
			dao.updateBook(book);
		} catch (Exception e) {
		}
		
	}
	//删除图书
	public void deleteBook(String id) {
		try {
			dao.delBook(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//批量删除
	public void deleteAllBook(String[] id) {
		try {
			dao.deleAllBook(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//按条件查询
	public List<Book> findProductByManyCondition(String id, String category,
			String name, String minprice, String maxprice) {
		try {
			return dao.findProductCondition(id,category,name,minprice,maxprice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public PageBean pageService(int currentPage, int pageSize) {
		
		try {
			int count=dao.getCount();//获得总条数
			int totalPage=(int) Math.ceil(count*1.0/pageSize);//获得总页数
			List<Book> list=dao.pageDatebase(currentPage,pageSize);//获得分页后的数据
			//把数据存入PageBean
			PageBean pb=new PageBean();
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			pb.setBook(list);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	//搜索框提示信息
	public List<Object> SearchAJAXInfoService(String name) {
		try {
			return dao.SearchAJAXInfo(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
