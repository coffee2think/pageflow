package com.erl.pageflow.inventory.model.vo;

import java.sql.Date;

public class Inventory implements java.io.Serializable {

	private static final long serialVersionUID = 1977195614477422843L;

	private int invenId;
	private int bookId;
	private int clientId;
	private int storeId;
	private int refundId;
	private int prevInbenId;
	private int increase;
	private int currInven;
	private Date invenDate;
	private String classify;
	private String remark;
	private String bookName;
	private String clientName;

	public Inventory() {
		super();
	}

	public Inventory(int invenId, int bookId, int clientId, int storeId, int refundId, int prevInbenId, int increase,
			int currInven, Date invenDate, String classify, String remark, String bookName, String clientName) {
		super();
		this.invenId = invenId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.storeId = storeId;
		this.refundId = refundId;
		this.prevInbenId = prevInbenId;
		this.increase = increase;
		this.currInven = currInven;
		this.invenDate = invenDate;
		this.classify = classify;
		this.remark = remark;
		this.bookName = bookName;
		this.clientName = clientName;
	}

	public int getInvenId() {
		return invenId;
	}

	public void setInvenId(int invenId) {
		this.invenId = invenId;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getRefundId() {
		return refundId;
	}

	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}

	public int getPrevInbenId() {
		return prevInbenId;
	}

	public void setPrevInbenId(int prevInbenId) {
		this.prevInbenId = prevInbenId;
	}

	public int getIncrease() {
		return increase;
	}

	public void setIncrease(int increase) {
		this.increase = increase;
	}

	public int getCurrInven() {
		return currInven;
	}

	public void setCurrInven(int currInven) {
		this.currInven = currInven;
	}

	public Date getInvenDate() {
		return invenDate;
	}

	public void setInvenDate(Date invenDate) {
		this.invenDate = invenDate;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Inventory [invenId=" + invenId + ", bookId=" + bookId + ", clientId=" + clientId + ", storeId="
				+ storeId + ", refundId=" + refundId + ", prevInbenId=" + prevInbenId + ", increase=" + increase
				+ ", currInven=" + currInven + ", invenDate=" + invenDate + ", classify=" + classify + ", remark="
				+ remark + ", bookName=" + bookName + ", clientName=" + clientName + "]";
	}

}
