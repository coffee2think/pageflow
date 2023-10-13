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
				+ state + ", classify=" + classify + "]";
	}

}
