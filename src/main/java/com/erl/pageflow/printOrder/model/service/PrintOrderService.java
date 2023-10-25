package com.erl.pageflow.printOrder.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

public interface PrintOrderService {
	public ArrayList<PrintOrder> selectPrintOrderByDate(Search search);
	
	public PrintOffice selectPrintOffice(int clientId);
	
	public Book selectBook(int bookId);
	
	public int selectPrintOrderListCount();
	
	public int selectMaxPrintOrderId();
	
	public ArrayList<PrintOrder> selectPrintOrderList(Paging paging);
	
	public int insertPrintOrder(PrintOrder printOrder);
	
	public int updatePrintOrder(PrintOrder PrintOrder);
	
	public int deletePrintOrder(int printOrder);
	
	public int selectPrintOrderCountByPrintName(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByPrintName(Search search);
	
	public int selectPrintOrderCountByBookName(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByBookName(Search search);
	
	public int selectPrintOrderCountBySDate(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderBySDate(Search search);
	
	public int selectPrintOrderCountByEDate(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByEDate(Search search);
	
	public int selectPrintOrderCountByPDate(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByPDate(Search search);
	
	public String selectPrintOrderClientName(int bookId);
	
	public String selectPrintOrderBookName(int bookId);
}
