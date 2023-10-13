package com.erl.pageflow.printOrder.model.vo;

import java.sql.Date;

public class PrintOrder implements java.io.Serializable{
	private static final long serialVersionUID = 5118637275192223873L;

	private int orderId;
	private int bookId;
	private int printId;
	private int empId;
	private String empName;
	private String classify;
	private String unit;
	private int quantity;
	private int price;
	private int amount;
	private String state;
	private Date orderDate;
	private Date endDate;
	private Date pubDate;
	
	public PrintOrder() {
		super();
	}

	public PrintOrder(int orderId, int bookId, int printId, int empId, String empName, String classify, String unit,
			int quantity, int price, int amount, String state, Date orderDate, Date endDate, Date pubDate) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.printId = printId;
		this.empId = empId;
		this.empName = empName;
		this.classify = classify;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.state = state;
		this.orderDate = orderDate;
		this.endDate = endDate;
		this.pubDate = pubDate;
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

	public int getPrintId() {
		return printId;
	}

	public void setPrintId(int printId) {
		this.printId = printId;
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

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PrintOrder [orderId=" + orderId + ", bookId=" + bookId + ", printId=" + printId + ", empId=" + empId
				+ ", empName=" + empName + ", classify=" + classify + ", unit=" + unit + ", quantity=" + quantity
				+ ", price=" + price + ", amount=" + amount + ", state=" + state + ", orderDate=" + orderDate
				+ ", endDate=" + endDate + ", pubDate=" + pubDate + "]";
	}
	
	
}

