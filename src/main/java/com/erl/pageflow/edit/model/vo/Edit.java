package com.erl.pageflow.edit.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Edit implements Serializable {
	private static final long serialVersionUID = -9208062950228834754L;
	
	private int editId;
	private int depId;
	private int empId;
	private String empName;
	private String bookName;
	private String editState;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String depName;
	
	public Edit() {
		super();
	}

	public Edit(int editId, int depId, String bookName, String editState, String depName) {
		super();
		this.editId = editId;
		this.depId = depId;
		this.bookName = bookName;
		this.editState = editState;
		this.depName = depName;
	}

	public Edit(int editId, int depId, int empId, String empName, String bookName, String editState, Date startDate,
			Date endDate, String depName) {
		super();
		this.editId = editId;
		this.depId = depId;
		this.empId = empId;
		this.empName = empName;
		this.bookName = bookName;
		this.editState = editState;
		this.startDate = startDate;
		this.endDate = endDate;
		this.depName = depName;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getEditState() {
		return editState;
	}

	public void setEditState(String editState) {
		this.editState = editState;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Edit [editId=" + editId + ", depId=" + depId + ", empId=" + empId + ", empName=" + empName
				+ ", bookName=" + bookName + ", editState=" + editState + ", startDate=" + startDate + ", endDate="
				+ endDate + ", depName=" + depName + "]";
	}

}
