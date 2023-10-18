package com.erl.pageflow.book.model.vo;

import java.sql.Date;

public class BookWithStock extends Book {
	
	private static final long serialVersionUID = -2167079701637296226L;
	
	private int stock;

	public BookWithStock() {
	}
	
	public BookWithStock(int bookId, int writerId, String bookName, String writerName, int bookPriceS, int bookPrice,
			String category, String isbn, String bookState, String engraving, Date pubDate, Date regDate, int stock) {
		super(bookId, writerId, bookName, writerName, bookPriceS, bookPrice, category, isbn, bookState, engraving, pubDate, regDate);
		this.stock = stock;
	}

	public BookWithStock(int bookId, int writerId, String bookName, String writerName, int bookPriceS, int bookPriceR,
			int bookPrice, String category, String isbn, String bookState, String engraving, Date pubDate,
			Date regDate, int stock) {
		super(bookId, writerId, bookName, writerName, bookPriceS, bookPriceR, bookPrice, category, isbn, bookState, engraving, pubDate, regDate);
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "BookWithStock [bookId=" + super.getBookId() + ", writerId=" + super.getWriterId() + ", bookName=" + super.getBookName() + ", writerName="
				+ super.getWriterName() + ", bookPriceS=" + super.getBookPriceS() + ", bookPriceR=" + super.getBookPriceR() + ", bookPrice=" + super.getBookPrice()
				+ ", category=" + super.getCategory() + ", isbn=" + super.getIsbn() + ", bookState=" + super.getBookState() + ", engraving=" + super.getEngraving()
				+ ", pubDate=" + super.getPubDate() + ", regDate=" + super.getRegDate() + ", stock=" + this.stock + "]";
	}

}
