package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bookstore.until.AccountThreadLocal;
import com.bookstore.until.C3P0Util;
import com.bookstore.user.Book;
import com.bookstore.user.OrderItem;
import com.bookstore.user.Orders;

public class BookDao {
	/**
	 * 对于下单后
	 * 批量修改商品数量
	 * @param order
	 * @throws Exception 
	 */
	public void updatePnum(Orders order) throws Exception {
		QueryRunner qr=new QueryRunner();
		List<OrderItem> orderitem = order.getOrderitem();
		Object[][] params=new Object[orderitem.size()][];
		for (int i = 0; i < params.length; i++) {
			params[i]=new Object[]{orderitem.get(i).getBuynum(),orderitem.get(i).getBook().getId()};
		}
		qr.batch(AccountThreadLocal.getConnection(),"update book set pnum=pnum-? where id=?", params);
	}
	
	/**
	 * 查找所有图书
	 * @return
	 * @throws Exception
	 */
	public List<Book> findAllBookInfo() throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book", new BeanListHandler<Book>(Book.class));
	}
	/**
	 * 添加图书
	 * @param book
	 * @throws Exception
	 */
	public void addBookInfo(Book book) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("insert into book values(?,?,?,?,?,?,?)",
		book.getId(),book.getName(),book.getPrice(),book.getCategory(),book.getPnum(),book.getImgurl(),book.getDescription());
	}
	/**
	 * 根据id查找图书
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Book findBookByIdInfo(String id) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class),id);
	}
	/**
	 * 修改图书信息
	 * @param book
	 * @throws Exception
	 */
	public void editBookInfo(Book book) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("update book set name=?,price=?,category=?,pnum=?,imgurl=?,description=? where id=?",
				book.getName(),book.getPrice(),book.getCategory(),book.getPnum(),book.getImgurl(),book.getDescription(),book.getId());
	}
	/**
	 * 根据id删除图书
	 * @param id
	 * @throws Exception
	 */
	public void deleteBookById(String id) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("delete from book where id=?",id);
	}
	/**
	 * 无上传表单项修改图书
	 * @param book
	 * @throws Exception
	 */
	public void editBookNoUp(Book book) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		qr.update("update book set name=?,price=?,category=?,pnum=?,description=? where id=?",
				book.getName(),book.getPrice(),book.getCategory(),book.getPnum(),book.getDescription(),book.getId());
	}
	/**
	 * 按条件查询
	 * @param id
	 * @param category
	 * @param name
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws Exception
	 */
	public List<Book> FindProductByManyCondition(String id, String category,
			String name, String minprice, String maxprice) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		List l=new ArrayList();
		String sql="select * from book where 1=1";
		if(!"".equals(id.trim())){
			sql+=" and id like ?";
			l.add("%"+id+"%");
		}
		if(!"".equals(category.trim())){
			sql+=" and category=?";
			l.add(category);
		}
		if(!"".equals(name.trim())){
			sql+=" and name like ?";
			l.add("%"+name+"%");
		}
		if(!"".equals(minprice.trim())){
			sql+=" and price>=?";
			l.add(minprice);
		}
		if(!"".equals(maxprice.trim())){
			sql+=" and price<=?";
			l.add(maxprice);
		}
		return qr.query(sql, new BeanListHandler<Book>(Book.class),l.toArray());
	}
	/**
	 * 根据条件获取数据条数
	 * @param category 
	 * @return
	 * @throws Exception
	 */
	public int getProductCount(String category) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		String sql="select count(*) from book";
		if(!"".equals(category)){
			sql+=" where category='"+category+"'";
		}
		long l = (Long) qr.query(sql,new ScalarHandler(1));
		return (int)l;
	}
	/**
	 * 根据类别获取分页数据
	 * @param currentPage
	 * @param pageSize
	 * @param category 
	 * @param category 
	 * @return
	 * @throws Exception
	 */
	public List<Book> getProductByPage(int currentPage, int pageSize, String category) throws Exception {
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		String sql="select * from book where 1=1";
		List list=new ArrayList();
		if(!"".equals(category)){
			sql+=" and category=?";
			list.add(category);
		}
		sql+=" limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		List<Book> book = qr.query(sql, new BeanListHandler<Book>(Book.class),list.toArray());
		return book;
	}
	
	
	
}
