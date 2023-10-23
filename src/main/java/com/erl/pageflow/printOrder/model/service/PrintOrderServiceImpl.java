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
	public int updatePrintOrder(PrintOrder clientId) {
		return printOrderDao.updatePrintOrder(clientId);
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
	public int deletePrintOrder(int orderId) {
		return printOrderDao.deletePrintOrder(orderId);
	}
	
	@Override
	public int selectPrintOrderCountByClientId(Search search) {
		return printOrderDao.selectPrintOrderCountByClientId(search);
	}
	
	@Override
	public int selectPrintOrderCountByPrintName(Search search) {
		return printOrderDao.selectPrintOrderCountByPrintName(search);
	}
	
	@Override
	public int selectPrintOrderCountByBookId(Search search) {
		return printOrderDao.selectPrintOrderCountByBookId(search);
	}
	
	@Override
	public int selectPrintOrderCountByBookName(Search search) {
		return printOrderDao.selectPrintOrderCountByBookName(search);
	}
	
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByClientId(Search search){
		return printOrderDao.selectPrintOrderByClientId(search);
	}
	
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByPrintName(Search search){
		return printOrderDao.selectPrintOrderByPrintName(search);
	}
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByBookId(Search search){
		return printOrderDao.selectPrintOrderByBookId(search);
	}
	@Override
	public ArrayList<PrintOrder> selectPrintOrderByBookName(Search search){
		return printOrderDao.selectPrintOrderByBookName(search);
	}
}





