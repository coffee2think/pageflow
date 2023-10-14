package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BookOrder extends Trade {
	
	private static final long serialVersionUID = -6450254897030418120L;

	public BookOrder() {
	}
	
	public BookOrder(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
	}
	
	public BookOrder(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify, String bookName, int bookPrice, int totalPrice, String bookStoreName) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify, bookName, bookPrice, totalPrice, bookStoreName);
	}
	
	@Override
	public String toString() {
		return "BookOrder [tradeId=" + this.getTradeId() + ", bookId=" + this.getBookId() + ", clientId=" + this.getClientId()
				+ ", empId=" + this.getEmpId() + ", empName=" + this.getEmpName() + ", orderQuantity=" + this.getOrderQuantity()
				+ ", orderDate=" + this.getOrderDate() + ", state=" + this.getState() + ", classify=" + this.getClassify()
				+ ", bookName=" + this.getBookName() + ", bookPrice=" + this.getBookPrice() + ", totalPrice=" + this.getTotalPrice()
				+ ", bookStoreName=" + this.getBookStoreName() + "]";
	}
	
}
