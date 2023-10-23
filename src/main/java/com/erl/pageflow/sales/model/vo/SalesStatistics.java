package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;

public class SalesStatistics implements Serializable {

	private static final long serialVersionUID = -2619233351122707581L;
	
	private String bookId;
	private String bookName;
	private String salesMonth01;
	private String salesMonth02;
	private String salesMonth03;
	private String salesMonth04;
	private String salesMonth05;
	private String salesMonth06;
	private String salesMonth07;
	private String salesMonth08;
	private String salesMonth09;
	private String salesMonth10;
	private String salesMonth11;
	private String salesMonth12;
	
	public SalesStatistics() {
		super();
	}

	public SalesStatistics(String bookId, String bookName, String salesMonth01, String salesMonth02,
			String salesMonth03, String salesMonth04, String salesMonth05, String salesMonth06, String salesMonth07,
			String salesMonth08, String salesMonth09, String salesMonth10, String salesMonth11, String salesMonth12) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.salesMonth01 = salesMonth01;
		this.salesMonth02 = salesMonth02;
		this.salesMonth03 = salesMonth03;
		this.salesMonth04 = salesMonth04;
		this.salesMonth05 = salesMonth05;
		this.salesMonth06 = salesMonth06;
		this.salesMonth07 = salesMonth07;
		this.salesMonth08 = salesMonth08;
		this.salesMonth09 = salesMonth09;
		this.salesMonth10 = salesMonth10;
		this.salesMonth11 = salesMonth11;
		this.salesMonth12 = salesMonth12;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getSalesMonth01() {
		return salesMonth01;
	}

	public void setSalesMonth01(String salesMonth01) {
		this.salesMonth01 = salesMonth01;
	}

	public String getSalesMonth02() {
		return salesMonth02;
	}

	public void setSalesMonth02(String salesMonth02) {
		this.salesMonth02 = salesMonth02;
	}

	public String getSalesMonth03() {
		return salesMonth03;
	}

	public void setSalesMonth03(String salesMonth03) {
		this.salesMonth03 = salesMonth03;
	}

	public String getSalesMonth04() {
		return salesMonth04;
	}

	public void setSalesMonth04(String salesMonth04) {
		this.salesMonth04 = salesMonth04;
	}

	public String getSalesMonth05() {
		return salesMonth05;
	}

	public void setSalesMonth05(String salesMonth05) {
		this.salesMonth05 = salesMonth05;
	}

	public String getSalesMonth06() {
		return salesMonth06;
	}

	public void setSalesMonth06(String salesMonth06) {
		this.salesMonth06 = salesMonth06;
	}

	public String getSalesMonth07() {
		return salesMonth07;
	}

	public void setSalesMonth07(String salesMonth07) {
		this.salesMonth07 = salesMonth07;
	}

	public String getSalesMonth08() {
		return salesMonth08;
	}

	public void setSalesMonth08(String salesMonth08) {
		this.salesMonth08 = salesMonth08;
	}

	public String getSalesMonth09() {
		return salesMonth09;
	}

	public void setSalesMonth09(String salesMonth09) {
		this.salesMonth09 = salesMonth09;
	}

	public String getSalesMonth10() {
		return salesMonth10;
	}

	public void setSalesMonth10(String salesMonth10) {
		this.salesMonth10 = salesMonth10;
	}

	public String getSalesMonth11() {
		return salesMonth11;
	}

	public void setSalesMonth11(String salesMonth11) {
		this.salesMonth11 = salesMonth11;
	}

	public String getSalesMonth12() {
		return salesMonth12;
	}

	public void setSalesMonth12(String salesMonth12) {
		this.salesMonth12 = salesMonth12;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalesStatistics [bookId=" + bookId + ", bookName=" + bookName + ", salesMonth01=" + salesMonth01
				+ ", salesMonth02=" + salesMonth02 + ", salesMonth03=" + salesMonth03 + ", salesMonth04=" + salesMonth04
				+ ", salesMonth05=" + salesMonth05 + ", salesMonth06=" + salesMonth06 + ", salesMonth07=" + salesMonth07
				+ ", salesMonth08=" + salesMonth08 + ", salesMonth09=" + salesMonth09 + ", salesMonth10=" + salesMonth10
				+ ", salesMonth11=" + salesMonth11 + ", salesMonth12=" + salesMonth12 + "]";
	}
	
}
