package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Sales extends Trade {

	private static final long serialVersionUID = -7081376351331977888L;
	
	private int collectedAmount;
	private Date collectDate;
	
	public Sales() {}
	
	public Sales(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
	}
	
	public Sales(int tradeId, int bookId, int clientId, int empId, String empName, int orderQuantity, Date orderDate,
			String state, String classify, int collectedAmount, Date collectDate) {
		super(tradeId, bookId, clientId, empId, empName, orderQuantity, orderDate, state, classify);
		this.collectedAmount = collectedAmount;
		this.collectDate = collectDate;
	}

	public int getCollectedAmount() {
		return collectedAmount;
	}

	public void setCollectedAmount(int collectedAmount) {
		this.collectedAmount = collectedAmount;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	@Override
	public String toString() {
		return "Sales [tradeId=" + super.getTradeId() + ", bookId=" + super.getBookId() + ", clientId=" + super.getClientId() + ", empId=" + super.getEmpId()
		+ ", empName=" + super.getEmpName() + ", orderQuantity=" + super.getOrderQuantity() + ", orderDate=" + super.getOrderDate() + ", state="
		+ super.getState() + ", classify=" + super.getClassify() + ", collectedAmount=" + collectedAmount + ", collectDate=" + collectDate + "]";
	}
	
}
