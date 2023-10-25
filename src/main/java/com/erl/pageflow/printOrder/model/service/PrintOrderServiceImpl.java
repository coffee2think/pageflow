package com.erl.pageflow.printOrder.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.dao.PrintOrderDao;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Service("printOrderService")
public class PrintOrderServiceImpl implements PrintOrderService {
		
	@Autowired
	private PrintOrderDao printOrderDao;

	@Override
	public ArrayList<PrintOrder> selectPrintOrderByDate(Search search) {
		return printOrderDao.selectPrintOrderByDate(search);
	}
	
	@Override
	public PrintOffice selectPrintOffice(int clientId) {
		return printOrderDao.selectPrintOffice(clientId);
	}
	
	@Override
	public Book selectBook(int bookId) {
		return printOrderDao.selectBook(bookId);
	}
	
	@Override
	public int selectPrintOrderListCount() {
		return printOrderDao.selectPrintOrderListCount();
	}
	
	@Override
	public ArrayList<PrintOrder> selectPrintOrderList(Paging paging){
		return printOrderDao.selectPrintOrderList(paging);
	}
	
	@Override
	public int selectMaxPrintOrderId() {
		return printOrderDao.selectMaxPrintOrderId();
	}
	
	@Override
	public int insertPrintOrder(PrintOrder printOrder) {
		return printOrderDao.insertPrintOrder(printOrder);
	}
	
	@Override
	public int updatePrintOrder(PrintOrder PrintOrder) {
		return printOrderDao.updatePrintOrder(PrintOrder);
	}
	
	@Override
	public int deletePrintOrder(int printOrder) {
		return printOrderDao.deletePrintOrder(printOrder);
	}
	
	@Override
	public int selectPrintOrderCountByPrintName(Search search) {
		return printOrderDao.selectPrintOrderCountByPrintName(search);
	}
	
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByPrintName(Search search){
		return printOrderDao.selectPrintOrderByPrintName(search);
	}
	
	@Override
	public int selectPrintOrderCountByBookName(Search search) {
		return printOrderDao.selectPrintOrderCountByBookName(search);
	}
	
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByBookName(Search search){
		return printOrderDao.selectPrintOrderByBookName(search);
	}
	
	@Override
	public int selectPrintOrderCountBySDate(Search search) {
		return printOrderDao.selectPrintOrderCountBySDate(search);
	}
	
	//시작날짜로 검색
	@Override
	public ArrayList<PrintOrder> selectPrintOrderBySDate(Search search) {
		return printOrderDao.selectPrintOrderBySDate(search);
	}
	
	@Override
	public int selectPrintOrderCountByEDate(Search search) {
		return printOrderDao.selectPrintOrderCountByEDate(search);
	}
	
	//마감날짜로 검색
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByEDate(Search search) {
		return printOrderDao.selectPrintOrderByEDate(search);
	}
	
	@Override
	public int selectPrintOrderCountByPDate(Search search) {
		return printOrderDao.selectPrintOrderCountByPDate(search);
	}
	
	//출간날짜로 검색
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByPDate(Search search) {
		return printOrderDao.selectPrintOrderByPDate(search);
	}
	
	@Override
	public String selectPrintOrderClientName(int bookId) {
		return printOrderDao.selectPrintOrderClientName(bookId);
	}
	
	@Override
	public String selectPrintOrderBookName(int bookId) {
		return printOrderDao.selectPrintOrderBookName(bookId);
	}
	
}





