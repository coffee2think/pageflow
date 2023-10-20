package com.erl.pageflow.refund.model.vo;

import java.sql.Date;

public class Refund implements java.io.Serializable {

	private static final long serialVersionUID = -8529517971691183610L;

	private int refundId;
	private int bookId;
	private int clientId;
	private int empId;
	private String empName;
	private int refundNum;
	private Date refundDate;
	private int refundAmount;
	private String refundState;
	private String remark;
	private String bookName;
	private String clientName;
	private String clientAddredd;
	private int bookPrice;
	private int prevInvenId;
	private int currInven;
	private int storageId;

	public Refund() {
		super();
	}

	public Refund(int refundId, int bookId, int clientId, int empId, String empName, int refundNum, Date refundDate,
			int refundAmount, String refundState, String remark, String bookName, String clientName,
			String clientAddredd, int bookPrice, int prevInvenId, int currInven, int storageId) {
		super();
		this.refundId = refundId;
		this.bookId = bookId;
		this.clientId = clientId;
		this.empId = empId;
		this.empName = empName;
		this.refundNum = refundNum;
		this.refundDate = refundDate;
		this.refundAmount = refundAmount;
		this.refundState = refundState;
		this.remark = remark;
		this.bookName = bookName;
		this.clientName = clientName;
		this.clientAddredd = clientAddredd;
		this.bookPrice = bookPrice;
		this.prevInvenId = prevInvenId;
		this.currInven = currInven;
		this.storageId = storageId;
	}

	public int getStorageId() {
		return storageId;
	}

	public void setStorageId(int storageId) {
		this.storageId = storageId;
	}

	public int getPrevInvenId() {
		return prevInvenId;
	}

	public void setPrevInvenId(int prevInvenId) {
		this.prevInvenId = prevInvenId;
	}

	public int getCurrInven() {
		return currInven;
	}

	public void setCurrInven(int currInven) {
		this.currInven = currInven;
	}

	public String getClientAddredd() {
		return clientAddredd;
	}

	public void setClientAddredd(String clientAddredd) {
		this.clientAddredd = clientAddredd;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getRefundId() {
		return refundId;
	}

	public void setRefundId(int refundId) {
		this.refundId = refundId;
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

	public int getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(int refundNum) {
		this.refundNum = refundNum;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
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

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Refund [refundId=" + refundId + ", bookId=" + bookId + ", clientId=" + clientId + ", empId=" + empId
				+ ", empName=" + empName + ", refundNum=" + refundNum + ", refundDate=" + refundDate + ", refundAmount="
				+ refundAmount + ", refundState=" + refundState + ", remark=" + remark + ", bookName=" + bookName
				+ ", clientName=" + clientName + ", bookPrice=" + bookPrice + "]";
	}

}
