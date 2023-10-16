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
	public ArrayList<Client> selectClientList(Paging paging) {
		return salesDao.selectClientList(paging);
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
	public int deleteBookOrder(int tradeId) {
		return salesDao.deleteBookOrder(tradeId);
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

	

}
