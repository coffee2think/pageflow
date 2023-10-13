package com.erl.pageflow.sales.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;

public interface SalesService {
	public ArrayList<BookOrder> selectBookOrderDate(Search search);
	public Book selectBook(int bookId);
	public BookStore selectBookStore(int clientId);

}
