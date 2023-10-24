package com.erl.pageflow.sales.model.vo;

import java.io.Serializable;

public class Rank implements Serializable {

	private static final long serialVersionUID = -4884422816502173175L;
	
	private int rank;
	private String bookName;
	private String writer;
	private String isbn;
	private String category;
	private String bookType;
	
	public Rank() {
	}

	public Rank(int rank, String bookName, String writer, String isbn, String category, String bookType) {
		this.rank = rank;
		this.bookName = bookName;
		this.writer = writer;
		this.isbn = isbn;
		this.category = category;
		this.bookType = bookType;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Rank [rank=" + rank + ", bookName=" + bookName + ", writer=" + writer + ", isbn=" + isbn + ", category="
				+ category + ", bookType=" + bookType + "]";
	}
	
}
