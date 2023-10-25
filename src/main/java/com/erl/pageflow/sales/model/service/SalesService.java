package com.erl.pageflow.sales.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.vo.BookForSales;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Rank;
import com.erl.pageflow.sales.model.vo.Sales;
import com.erl.pageflow.sales.model.vo.SalesStatistics;

public interface SalesService {
	public int selectBookOrderCountByDate(Search search);
	public ArrayList<BookOrder> selectBookOrderByDate(Search search);
	public int selectSalesCountByDate(Search search);
	public ArrayList<Sales> selectSalesListByDate(Search search);
	public int updateSales(Sales sales);
	public BookForSales selectBook(int bookId);
	public BookStore selectBookStore(int clientId);
	public int selectClientListCount();
	public int selectClientCountByStartDate(Search search);
	public int selectClientCountByEndDate(Search search);
	public ArrayList<Client> selectClientList(Paging paging);
	public ArrayList<Client> selectClientByStartDate(Search search);
	public ArrayList<Client> selectClientByEndDate(Search search);
	public int insertBookOrder(BookOrder bookOrder);
	public int updateBookOrder(BookOrder bookOrder);
	public int deleteBookOrder(BookOrder bookOrder);
	public int insertClient(Client client);
	public int updateClient(Client client);
	public int deleteClient(int clientId);
	public int selectBookOrderCountByBook(Search search);
	public int selectBookOrderCountByBookStore(Search search);
	public int selectBookOrderCountByLocation(Search search);
	public ArrayList<BookOrder> selectBookOrderByBook(Search search);
	public ArrayList<BookOrder> selectBookOrderByBookStore(Search search);
	public ArrayList<BookOrder> selectBookOrderByLocation(Search search);
	public int selectMaxOrderId();
	public ArrayList<SalesStatistics> selectSalesForStats(int year);
	public int insertSales(Sales sales);
	public int deleteSales(int salesId);
	public ArrayList<Integer> selectYears();
	public ArrayList<Rank> selectRank();
	public ArrayList<BookOrder> selectNewTop3();
}
