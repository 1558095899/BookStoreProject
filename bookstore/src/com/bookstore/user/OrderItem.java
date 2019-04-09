package com.bookstore.user;
//订单表项
public class OrderItem {
	 private String order_id;
	 private String product_id;
	 private int buynum;
	 private Orders order;
	 private Book book;
	 
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	 
}	
