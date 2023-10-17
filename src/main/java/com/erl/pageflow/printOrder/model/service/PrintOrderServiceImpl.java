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
	public PrintOffice selectPrintOffice(int printId) {
		return printOrderDao.selectPrintOffice(printId);
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
	public int poinsert(PrintOrder printOrder) {
		return printOrderDao.poinsert(printOrder);
	}
}
