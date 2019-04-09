package com.JsonTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import com.book.Book;
import com.bookdao.C3P0Util;

public class JsonTest {
	@Test
	public void test1(){
		Book b=new Book();
		b.setId("1");
		b.setPrice(20);
		b.setName("java");
		//json封装格式
		String str=JSONObject.fromObject(b).toString();
		System.out.println(str);//{"category":"","description":"","id":"1","name":"java","pnum":0,"price":20}
	}
	@Test
	public void test2(){
		List<Book> list=new ArrayList<Book>();
		Book b1=new Book();
		b1.setId("2");
		b1.setName("ee");
		b1.setPrice(20);
		
		Book b2=new Book();
		b2.setId("3");
		b2.setName("we");
		b2.setPrice(20);
		list.add(b1);
		list.add(b2);
		
		String s = JSONArray.fromObject(list).toString();
		System.out.println(s);
	}
	@Test
	public void test3() throws Exception{
		QueryRunner qr=new QueryRunner(C3P0Util.getDateSource());
		List<Book> list = qr.query("select * from book", new BeanListHandler<Book>(Book.class));
		//筛选不要的信息
		JsonConfig jc=new JsonConfig();
		jc.setExcludes(new String[]{"category","description","pnum"});
		
		String s = JSONArray.fromObject(list,jc).toString();
		System.out.println(s);
	}
}
