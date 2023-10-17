package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BookOrder implements Serializable {
	
	private static final long serialVersionUID = -6450254897030418120L;

	private int orderId;
	private int bookId;
	private int clientId;
	private int empId;
	private int orderQuantity;
	private Date orderDate;
	private Date modifyDate;
	private String state;
	
	private String empName;
	private String bookName;
	private int bookPrice;
	private int totalPrice;
	private String bookStoreName;
	
	public BookOrder() {
	}

	public BookOrder(int orderId, int bookId, int clientId, int empId, int orderQuantity, Date orderDate,
			String state) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.empId = empId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.state = state;
	}

	public BookOrder(int orderId, int bookId, int clientId, int empId, int orderQuantity, Date orderDate,
			Date modifyDate, String state, String empName, String bookName, int bookPrice, int totalPrice,
			String bookStoreName) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.empId = empId;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
		this.modifyDate = modifyDate;
		this.state = state;
		this.empName = empName;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.totalPrice = totalPrice;
		this.bookStoreName = bookStoreName;
	}
	
	public void calcTotalPrice() {
		this.totalPrice = this.getBookPrice() * this.getOrderQuantity();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BookOrder [orderId=" + orderId + ", bookId=" + bookId + ", clientId=" + clientId + ", empId=" + empId
				+ ", orderQuantity=" + orderQuantity + ", orderDate=" + orderDate + ", modifyDate=" + modifyDate
				+ ", state=" + state + ", empName=" + empName + ", bookName=" + bookName + ", bookPrice=" + bookPrice
				+ ", totalPrice=" + totalPrice + ", bookStoreName=" + bookStoreName + "]";
	}
	
}
