package com.erl.pageflow.sales.model.vo;

import java.sql.Date;

public class Sales extends BookOrder {

	private static final long serialVersionUID = 6538424039103518592L;
	
	private int salesId;
	private int collectedAmount;
	private Date collectDate;
	private Date createDate;
	
	public Sales() {}
	
	public Sales(int orderId, int bookId, int clientId, int empId, int orderQuantity, Date orderDate,
			String state, int salesId, int collectedAmount, Date collectDate, Date createDate) {
		super(orderId, bookId, clientId, empId, orderQuantity, orderDate, state);
		this.salesId = salesId;
		this.collectedAmount = collectedAmount;
		this.collectDate = collectDate;
		this.createDate = createDate;
	}
	
	public Sales(int orderId, int bookId, int clientId, int empId, int orderQuantity, Date orderDate,
			Date modifyDate, String state, String empName, String bookName, int bookPrice, int totalPrice,
			String bookStoreName, int salesId, int collectedAmount, Date collectDate, Date createDate) {
		super(orderId, bookId, clientId, empId, orderQuantity, orderDate, modifyDate, state, empName, bookName, bookPrice, totalPrice, bookStoreName);
		this.salesId = salesId;
		this.collectedAmount = collectedAmount;
		this.collectDate = collectDate;
		this.createDate = createDate;
	}
	
	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Sales [orderId=" + getOrderId() + ", bookId=" + getBookId() + ", clientId=" + getClientId() + ", empId=" + getEmpId()
				+ ", orderQuantity=" + getOrderQuantity() + ", orderDate=" + getOrderDate() + ", modifyDate=" + getModifyDate()
				+ ", state=" + getState() + ", empName=" + getEmpName() + ", bookName=" + getBookName() + ", bookPrice=" + getBookPrice()
				+ ", totalPrice=" + getTotalPrice() + ", bookStoreName=" + getBookStoreName() + ", salesId=" + salesId
				+ ", collectedAmount=" + collectedAmount + ", collectDate=" + collectDate + ", createDate=" + createDate + "]";
	}
	
}
