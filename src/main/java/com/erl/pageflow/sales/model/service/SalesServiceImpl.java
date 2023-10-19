package com.erl.pageflow.sales.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.dao.SalesDao;
import com.erl.pageflow.sales.model.vo.BookForSales;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Sales;

@Service("salesService")
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesDao salesDao;

	@Override
	public int selectBookOrderCountByDate(Search search) {
		return salesDao.selectBookOrderCountByDate(search);
	}
	
	@Override
	public ArrayList<BookOrder> selectBookOrderByDate(Search search) {
		return salesDao.selectBookOrderByDate(search);
	}
	
	@Override
	public int selectSalesCountByDate(Search search) {
		return salesDao.selectSalesCountByDate(search);
	}
	
	@Override
	public ArrayList<Sales> selectSalesListByDate(Search search) {
		return salesDao.selectSalesListByDate(search);
	}
	
	@Override
	public int updateSales(Sales sales) {
		return salesDao.updateSales(sales);
	}

	@Override
	public BookForSales selectBook(int bookId) {
		return salesDao.selectBook(bookId);
	}

	@Override
	public BookStore selectBookStore(int clientId) {
		return salesDao.selectBookStore(clientId);
	}

	@Override
	public int selectClientListCount() {
		return salesDao.selectClientListCount();
	}
	
	@Override
	public int selectClientCountByStartDate(Search search) {
		return salesDao.selectClientCountByStartDate(search);
	}

	@Override
	public int selectClientCountByEndDate(Search search) {
		return salesDao.selectClientCountByEndDate(search);
	}
	
	@Override
	public ArrayList<Client> selectClientList(Paging paging) {
		return salesDao.selectClientList(paging);
	}
	
	@Override
	public ArrayList<Client> selectClientByStartDate(Search search) {
		return salesDao.selectClientByStartDate(search);
	}

	@Override
	public ArrayList<Client> selectClientByEndDate(Search search) {
		return salesDao.selectClientByEndDate(search);
	}

	@Override
	public int insertBookOrder(BookOrder bookOrder) {
		return salesDao.insertBookOrder(bookOrder);
	}

	@Override
	public int updateBookOrder(BookOrder bookOrder) {
		return salesDao.updateBookOrder(bookOrder);
	}
	
	@Override
	public int deleteBookOrder(int orderId) {
		return salesDao.deleteBookOrder(orderId);
	}

	@Override
	public int insertClient(Client client) {
		return salesDao.insertClient(client);
	}

	@Override
	public int updateClient(Client client) {
		return salesDao.updateClient(client);
	}

	@Override
	public int deleteClient(int clientId) {
		return salesDao.deleteClient(clientId);
	}

	@Override
	public int selectBookOrderCountByBook(Search search) {
		return salesDao.selectBookOrderCountByBook(search);
	}

	@Override
	public int selectBookOrderCountByBookStore(Search search) {
		return salesDao.selectBookOrderCountByBookStore(search);
	}

	@Override
	public int selectBookOrderCountByLocation(Search search) {
		return salesDao.selectBookOrderCountByLocation(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByBook(Search search) {
		return salesDao.selectBookOrderByBook(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByBookStore(Search search) {
		return salesDao.selectBookOrderByBookStore(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByLocation(Search search) {
		return salesDao.selectBookOrderByLocation(search);
	}

	@Override
	public int selectMaxOrderId() {
		return salesDao.selectMaxOrderId();
	}

	@Override
	public int selectSalesCountForStats() {
		return salesDao.selectSalesCountForStats();
	}

	@Override
	public ArrayList<Sales> selectSalesForStats(Paging paging) {
		return salesDao.selectSalesForStats(paging);
	}

}
