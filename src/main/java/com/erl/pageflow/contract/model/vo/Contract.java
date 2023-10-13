package com.erl.pageflow.contract.model.vo;

import java.sql.Date;

public class Contract implements java.io.Serializable {
	private static final long serialVersionUID = 3730106916488432512L;
	
	private int contrId;
	private int empId;
	private int writerId;
	private String bookName;
	private String empName;
	private java.sql.Date contrDate;
	private String contrDoc;
	private String contrState;
	
	public Contract() {
		super();
	}

	public Contract(int contrId, int empId, int writerId, String bookName, String empName, String contrState) {
		super();
		this.contrId = contrId;
		this.empId = empId;
		this.writerId = writerId;
		this.bookName = bookName;
		this.empName = empName;
		this.contrState = contrState;
	}

	public Contract(int contrId, int empId, int writerId, String bookName, String empName, Date contrDate,
			String contrDoc, String contrState) {
		super();
		this.contrId = contrId;
		this.empId = empId;
		this.writerId = writerId;
		this.bookName = bookName;
		this.empName = empName;
		this.contrDate = contrDate;
		this.contrDoc = contrDoc;
		this.contrState = contrState;
	}

	public int getContrId() {
		return contrId;
	}

	public void setContrId(int contrId) {
		this.contrId = contrId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getContrDate() {
		return contrDate;
	}

	public void setContrDate(Date contrDate) {
		this.contrDate = contrDate;
	}

	public String getContrDoc() {
		return contrDoc;
	}

	public void setContrDoc(String contrDoc) {
		this.contrDoc = contrDoc;
	}

	public String getContrState() {
		return contrState;
	}

	public void setContrState(String contrState) {
		this.contrState = contrState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Contract [contrId=" + contrId + ", empId=" + empId + ", writerId=" + writerId + ", bookName=" + bookName
				+ ", empName=" + empName + ", contrDate=" + contrDate + ", contrDoc=" + contrDoc + ", contrState="
				+ contrState + "]";
	}
	
}
