package com.erl.pageflow.sales.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.vo.BookForSales;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Sales;

public interface SalesService {
	public ArrayList<BookOrder> selectBookOrderByDate(Search search);
	public ArrayList<Sales> selectSalesByDate(Search search);
	public BookForSales selectBook(int bookId);
	public BookStore selectBookStore(int clientId);
	public int selectClientListCount();
	public ArrayList<Client> selectClientList(Paging paging);
	public int insertBookOrder(BookOrder bookOrder);
	public int updateBookOrder(BookOrder bookOrder);

}
