package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BookOrder extends Trade {
	
	private static final long serialVersionUID = -6450254897030418120L;

	private String bookName;
	private int bookPrice;
	private int totalPrice;
	private String bookStoreName;
	
	public BookOrder() {
	}
	
	public BookOrder(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
	}
	
	public BookOrder(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify, String bookName, int bookPrice, int totalPrice, String bookStoreName) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.totalPrice = totalPrice;
		this.bookStoreName = bookStoreName;
	}
	
	public void calcTotalPrice() {
		totalPrice = this.getBookPrice() * this.getOrderQuantity();
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getBookStoreName() {
		return bookStoreName;
	}

	public void setBookStoreName(String bookStoreName) {
		this.bookStoreName = bookStoreName;
	}

	@Override
	public String toString() {
		return "BookOrder [tradeId=" + super.getTradeId() + ", bookId=" + super.getBookId() + ", clientId=" + super.getClientId() + ", empId=" + super.getEmpId()
				+ ", empName=" + super.getEmpName() + ", orderQuantity=" + super.getOrderQuantity() + ", orderDate=" + super.getOrderDate() + ", state="
				+ super.getState() + ", classify=" + super.getClassify() + ", bookName=" + this.bookName + ", bookPrice=" + this.bookPrice
				+ ", totalPrice=" + this.totalPrice + ", bookStoreName=" + this.bookStoreName + "]";
	}
	
	
}
