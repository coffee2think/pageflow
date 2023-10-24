package com.erl.pageflow.employee.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class SearchEmp implements Serializable {

	private static final long serialVersionUID = -806504296447948950L;

	private int empId;
	private int depId;
	private String posName;
	private String deptName;
	private Date begin;
	private Date end;
	private int startRow;
	private int endRow;
	private Date week;
	private Date month;
	private String searchType = null;
	private String apType = null;

	public SearchEmp() {
	}
	
	public SearchEmp(int empId, int depId, String posName, String deptName, Date begin, Date end, int startRow,
			int endRow, Date week, Date month, String searchType, String apType) {
		super();
		this.empId = empId;
		this.depId = depId;
		this.posName = posName;
		this.deptName = deptName;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
		this.week = week;
		this.month = month;
		this.searchType = searchType;
		this.apType = apType;
	}


	public int getEmpId() {
		return empId;
	}


	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public int getDepId() {
		return depId;
	}


	public void setDepId(int depId) {
		this.depId = depId;
	}


	public String getPosName() {
		return posName;
	}


	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Date getBegin() {
		return begin;
	}


	public void setBegin(Date begin) {
		this.begin = begin;
	}


	public Date getEnd() {
		return end;
	}


	public void setEnd(Date end) {
		this.end = end;
	}


	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Date getWeek() {
		return week;
	}

	public void setWeek(Date week) {
		this.week = week;
	}

	public Date getMonth() {
		return month;
	}


	public void setMonth(Date month) {
		this.month = month;
	}


	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getApType() {
		return apType;
	}

	public void setApType(String apType) {
		this.apType = apType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SearchEmp [empId=" + empId + ", depId=" + depId + ", posName=" + posName + ", deptName=" + deptName
				+ ", begin=" + begin + ", end=" + end + ", startRow=" + startRow + ", endRow=" + endRow + ", week="
				+ week + ", month=" + month + ", searchType=" + searchType + ", apType=" + apType + "]";
	}

	

}
