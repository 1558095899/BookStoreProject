package com.bookdao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.book.Book;

public class BookDao {
	/**
	 * 查看图书信息
	 * @return
	 * @throws Exception
	 */
	public List<Book> lookBook() throws Exception{
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book", new BeanListHandler<Book>(Book.class));
	}
	/**
	 * 添加图书信息
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book) throws Exception{
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("insert into book(id,name,price,pnum,category,description,img_url) values(?,?,?,?,?,?,?)"
				,book.getId(),book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getImg_url());
	}
	/**
	 * 根据id查找对应图书信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Book findBookById(String id) throws Exception{
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class),id);
	}
	/**
	 * 修改图书信息
	 * @param book
	 * @throws Exception
	 */
	public void updateBook(Book book) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("update book set name=?,price=?,pnum=?,category=?,description=? where id=?"
				,book.getName(),book.getPrice(),book.getPnum(),book.getCategory(),book.getDescription(),book.getId());
	}
	/**
	 * 删除图书
	 * @param id
	 * @throws Exception
	 */
	public void delBook(String id) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("delete from book where id=?",id);
		
	}
	/**
	 * 批量删除
	 * @param id
	 * @throws Exception
	 */
	public void deleAllBook(String[] id) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		Object[][] params=new Object[id.length][];
		for(int i=0;i<id.length;i++){
			params[i]=new Object[]{id[i]};
		}
		qr.batch("delete from book where id=?", params);
	}
	/**
	 * 条件查询
	 * @param id
	 * @param category
	 * @param name
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws Exception
	 */
	public List<Book> findProductCondition(String id, String category, String name,
			String minprice, String maxprice) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		String sql="select * from book where 1=1";
		List list=new ArrayList();
		if(!"".equals(id.trim())){
			sql+=" and id like ?";
			list.add("%"+id+"%");
		}
		if(!"".equals(category.trim())){
			sql+=" and category=?";
			list.add(category);
		}
		if(!"".equals(name.trim())){
			sql+=" and name like ?";
			list.add("%"+name+"%");
		}
		if(!"".equals(minprice.trim())){
			sql+=" and price>?";
			list.add(minprice);
		}
		if(!"".equals(maxprice.trim())){
			sql+=" and price<?";
			list.add(maxprice);
		}
		return qr.query(sql,new BeanListHandler<Book>(Book.class),list.toArray());
	}
	/**
	 * 分页
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Book> pageDatebase(int currentPage, int pageSize) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book limit ?,?",new BeanListHandler<Book>(Book.class),(currentPage-1)*pageSize,pageSize);
		
	}
	/**
	 * 获得count
	 * @return
	 * @throws Exception
	 */
	public int getCount() throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		long l = (Long) qr.query("select count(*) from book", new ScalarHandler(1));//获得一行一列数据    即count数
		return (int)l;
	}
	/**
	 * 异步查询信息
	 * 搜索框提示信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Object> SearchAJAXInfo(String name) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book where name like ?", new ColumnListHandler(2),"%"+name+"%");
	}
	
}
