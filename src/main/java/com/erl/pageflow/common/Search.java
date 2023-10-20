package com.erl.pageflow.common;

import java.io.Serializable;
import java.sql.Date;

public class Search implements Serializable {

	private static final long serialVersionUID = -806504296447948950L;

	private int empId;
	private int depId;
	private String keyword;
	private Date begin;
	private Date end;
	private int startRow;
	private int endRow;
	private Date week;
	private Date month;
	private String searchType = null;
	private String apType = null;

	public Search() {
	}
	
	public Search(int startRow, int endRow) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	public Search(int empId, int depId, int startRow, int endRow) {
		super();
		this.empId = empId;
		this.depId = depId;
		this.startRow = startRow;
		this.endRow = endRow;
	}
	
	public Search(String keyword, Date begin, Date end, int startRow, int endRow, String searchType) {
		super();
		this.keyword = keyword;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
		this.week = week;
		this.month = month;
		this.searchType = searchType;
	}
	
	public Search(int empId, String keyword, Date begin, Date end, int startRow, int endRow, String searchType, String apType) {
		super();
		this.empId = empId;
		this.keyword = keyword;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
		this.week = week;
		this.month = month;
		this.searchType = searchType;
		this.apType = apType;
	}
	
	public Search(String keyword, Date begin, Date end, int startRow, int endRow) {
		super();
		this.keyword = keyword;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
		this.week = week;
		this.month = month;
	}

	public Search(String keyword, Date begin, Date end, int startRow, int endRow, Date week, Date month) {
		super();
		this.keyword = keyword;
		this.begin = begin;
		this.end = end;
		this.startRow = startRow;
		this.endRow = endRow;
		this.week = week;
		this.month = month;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	@Override
	public String toString() {
		return "Search [empId=" + empId + ", depId=" + depId + ", keyword=" + keyword + ", begin=" + begin + ", end="
				+ end + ", startRow=" + startRow + ", endRow=" + endRow + ", week=" + week + ", month=" + month
				+ ", searchType=" + searchType + ", apType=" + apType + "]";
	}

	
	

}
