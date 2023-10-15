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
	public int selectBookOrderCountByDate(Search search);
	public ArrayList<BookOrder> selectBookOrderByDate(Search search);
	public int selectSalesCountByDate(Search search);
	public ArrayList<Sales> selectSalesListByDate(Search search);
	public BookForSales selectBook(int bookId);
	public BookStore selectBookStore(int clientId);
	public int selectClientListCount();
	public ArrayList<Client> selectClientList(Paging paging);
	public int insertBookOrder(BookOrder bookOrder);
	public int updateBookOrder(BookOrder bookOrder);
	public int deleteBookOrder(int tradeId);
	public int insertClient(Client client);
	public int updateClient(Client client);
	public int deleteClient(int clientId);

}
