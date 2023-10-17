package com.erl.pageflow.common;

import java.io.Serializable;
import java.sql.Date;

public class Search implements Serializable {

	private static final long serialVersionUID = -806504296447948950L;

	private String keyword;
	private Date begin;
	private Date end;
	private int startRow;
	private int endRow;
	private Date week;
	private Date month;

	public Search() {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Search [keyword=" + keyword + ", begin=" + begin + ", end=" + end + ", startRow=" + startRow
				+ ", endRow=" + endRow + ", week=" + week + ", month=" + month + "]";
	}

}
