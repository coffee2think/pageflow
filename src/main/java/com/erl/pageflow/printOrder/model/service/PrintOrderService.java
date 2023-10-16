package com.erl.pageflow.printOrder.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

public interface PrintOrderService {
	public ArrayList<PrintOrder> selectPrintOrderByDate(Search search);
	
	public PrintOffice selectPrintOffice(int printId);
	
	public Book selectBook(int bookId);
	
	public int selectPrintOrderListCount();
	
	public ArrayList<PrintOrder> selectPrintOrderList(Paging paging);
	
}
