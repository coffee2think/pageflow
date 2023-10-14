package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Trade implements Serializable {
	
	private static final long serialVersionUID = -2214301094669946849L;
	
	private int tradeId;
	private int bookId;
	private int clientId;
	private int empId;
	private String empName;
	private int orderQuantity;
	private Date orderDate;
	private String state;
	private String classify;
	
	private String bookName;
	private int bookPrice;
	private int totalPrice;
	private String bookStoreName;
	
	public Trade() {}

	public Trade(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify) {
		this.tradeId = tradeId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.empId = empId;
		this.empName = empName;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.state = state;
		this.classify = classify;
	}
	
	public Trade(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify, String bookName, int bookPrice, int totalPrice, String bookStoreName) {
		this.tradeId = tradeId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.empId = empId;
		this.empName = empName;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.state = state;
		this.classify = classify;
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
	
	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", bookId=" + bookId + ", clientId=" + clientId + ", empId=" + empId
				+ ", empName=" + empName + ", orderQuantity=" + orderQuantity + ", orderDate=" + orderDate + ", state="
				+ state + ", classify=" + classify + ", bookName=" + bookName + ", bookPrice=" + bookPrice
				+ ", totalPrice=" + totalPrice + ", bookStoreName=" + bookStoreName + "]";
	}

}
