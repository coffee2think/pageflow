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
	
	public ArrayList<PrintOrder> selectPrintOrderList(Paging paging);
	
	public int updatePrintOrder(PrintOrder PrintOrder);
	
	public int selectMaxPrintOrderId();
	
	public int insertPrintOrder(PrintOrder printOrder);
	
	public int deletePrintOrder(int printOrder);
	
	public int selectPrintOrderCountByOrderId(int search);
	
	public int selectPrintOrderCountByPrintName(Search search);
	
	public int selectPrintOrderCountByBookId(int search);
	
	public int selectPrintOrderCountByBookName(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByOrderId(int search);
	
	public ArrayList<PrintOrder> selectPrintOrderByPrintName(Search search);
	
	public ArrayList<PrintOrder> selectPrintOrderByBookId(int search);
	
	public ArrayList<PrintOrder> selectPrintOrderByBookName(Search search);
}
