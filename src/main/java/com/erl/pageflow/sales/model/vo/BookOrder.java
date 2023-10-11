package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BookOrder extends Trade {
	
	private static final long serialVersionUID = -6450254897030418120L;

	public BookOrder() {
		super();
	}
	
	public BookOrder(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
	}

	@Override
	public String toString() {
		return "BookOrder [tradeId=" + super.getTradeId() + ", bookId=" + super.getBookId() + ", clientId=" + super.getClientId() + ", empId=" + super.getEmpId()
				+ ", empName=" + super.getEmpName() + ", orderQuantity=" + super.getOrderQuantity() + ", orderDate=" + super.getOrderDate() + ", state="
				+ super.getState() + ", classify=" + super.getClassify() + "]";
	}
	
	
}
