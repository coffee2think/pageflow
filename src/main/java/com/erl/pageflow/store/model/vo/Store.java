package com.erl.pageflow.store.model.vo;

import java.sql.Date;

public class Store implements java.io.Serializable {

	private static final long serialVersionUID = 8413043374622537949L;

	private int storeId;
	private int bookId;
	private int storageId;
	private int empId;
	private String empName;
	private int storeNum;
	private int storePrice;
	private Date storeDate;
	private String classify;

	public Store() {
		super();
	}

	public Store(int storeId, int bookId, int storageId, int empId, String empName, int storeNum, int storePrice,
			Date storeDate, String classify) {
		super();
		this.storeId = storeId;
		this.bookId = bookId;
		this.storageId = storageId;
		this.empId = empId;
		this.empName = empName;
		this.storeNum = storeNum;
		this.storePrice = storePrice;
		this.storeDate = storeDate;
		this.classify = classify;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getStorageId() {
		return storageId;
	}

	public void setStorageId(int storageId) {
		this.storageId = storageId;
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

	public int getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(int storeNum) {
		this.storeNum = storeNum;
	}

	public int getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(int storePrice) {
		this.storePrice = storePrice;
	}

	public Date getStoreDate() {
		return storeDate;
	}

	public void setStoreDate(Date storeDate) {
		this.storeDate = storeDate;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", bookId=" + bookId + ", storageId=" + storageId + ", empId=" + empId
				+ ", empName=" + empName + ", storeNum=" + storeNum + ", storePrice=" + storePrice + ", storeDate="
				+ storeDate + ", classify=" + classify + "]";
	}

}
