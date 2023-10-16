package com.erl.pageflow.book.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable {
	private static final long serialVersionUID = -6417677392956824886L;
	
	private int bookId;
	private int writerId;
	private String bookName;
	private String writerName;
	private int bookPriceS;
	private int bookPriceR;
	private int bookPrice;
	private String category;
	private String isbn;
	private String bookState;
	private String engraving;
	private Date pubDate;
	private Date regDate;
	
	public Book() {
		super();
	}

	public Book(int bookId, int writerId, String bookName, String writerName, int bookPriceS, int bookPriceR,
			int bookPrice, String category, String isbn, String bookState, String engraving, Date pubDate,
			Date regDate) {
		super();
		this.bookId = bookId;
		this.writerId = writerId;
		this.bookName = bookName;
		this.writerName = writerName;
		this.bookPriceS = bookPriceS;
		this.bookPriceR = bookPriceR;
		this.bookPrice = bookPrice;
		this.category = category;
		this.isbn = isbn;
		this.bookState = bookState;
		this.engraving = engraving;
		this.pubDate = pubDate;
		this.regDate = regDate;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getBookPriceS() {
		return bookPriceS;
	}

	public void setBookPriceS(int bookPriceS) {
		this.bookPriceS = bookPriceS;
	}

	public int getBookPriceR() {
		return bookPriceR;
	}

	public void setBookPriceR(int bookPriceR) {
		this.bookPriceR = bookPriceR;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}

	public String getEngraving() {
		return engraving;
	}

	public void setEngraving(String engraving) {
		this.engraving = engraving;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", writerId=" + writerId + ", bookName=" + bookName + ", writerName="
				+ writerName + ", bookPriceS=" + bookPriceS + ", bookPriceR=" + bookPriceR + ", bookPrice=" + bookPrice
				+ ", category=" + category + ", isbn=" + isbn + ", bookState=" + bookState + ", engraving=" + engraving
				+ ", pubDate=" + pubDate + ", regDate=" + regDate + "]";
	}
}
