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
import com.erl.pageflow.sales.model.vo.Rank;
import com.erl.pageflow.sales.model.vo.Sales;
import com.erl.pageflow.sales.model.vo.SalesStatistics;

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
	public int deleteBookOrder(BookOrder bookOrder) {
		return salesDao.deleteBookOrder(bookOrder);
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
	public ArrayList<SalesStatistics> selectSalesForStats(int year) {
		return salesDao.selectSalesForStats(year);
	}

	@Override
	public int insertSales(Sales sales) {
		return salesDao.insertSales(sales);
	}

	@Override
	public int deleteSales(int salesId) {
		return salesDao.deleteSales(salesId);
	}

	@Override
	public ArrayList<Integer> selectYears() {
		return salesDao.selectYears();
	}

	@Override
	public ArrayList<Rank> selectRank() {
		return salesDao.selectRank();
	}

	@Override
	public ArrayList<BookOrder> selectNewTop3() {
		return salesDao.selectNewTop3();
  }

  @Override
	public int selectBookOrderCountByState(Search search) {
		return salesDao.selectBookOrderCountByState(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByState(Search search) {
		return salesDao.selectBookOrderByState(search);
	}

	@Override
	public int selectSalesCountByBook(Search search) {
		return salesDao.selectSalesCountByBook(search);
	}

	@Override
	public int selectSalesCountByBookStore(Search search) {
		return salesDao.selectSalesCountByBookStore(search);
	}

	@Override
	public int selectSalesCountByLocation(Search search) {
		return salesDao.selectSalesCountByLocation(search);
	}

	@Override
	public ArrayList<Sales> selectSalesByBook(Search search) {
		return salesDao.selectSalesByBook(search);
	}

	@Override
	public ArrayList<Sales> selectSalesByBookStore(Search search) {
		return salesDao.selectSalesByBookStore(search);
	}

	@Override
	public ArrayList<Sales> selectSalesByLocation(Search search) {
		return salesDao.selectSalesByLocation(search);
	}

	@Override
	public int selectClientCountByName(Search search) {
		return salesDao.selectClientCountByName(search);
	}

	@Override
	public int selectClientCountByAddress(Search search) {
		return salesDao.selectClientCountByAddress(search);
	}

	@Override
	public int selectClientCountByType(Search search) {
		return salesDao.selectClientCountByType(search);
	}

	@Override
	public ArrayList<Client> selectClientByName(Search search) {
		return salesDao.selectClientByName(search);
	}

	@Override
	public ArrayList<Client> selectClientByAddress(Search search) {
		return salesDao.selectClientByAddress(search);
	}

	@Override
	public ArrayList<Client> selectClientByType(Search search) {
		return salesDao.selectClientByType(search);
	}

}
