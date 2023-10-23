package com.erl.pageflow.printCalc.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printCalc.model.vo.PrintCalc;
import com.erl.pageflow.sales.model.vo.PrintOffice;

public interface PrintCalcService {

	public int selectPrintCalcListCount();
	
	public ArrayList<PrintCalc> selectPrintCalcByDate(Search search);
	
	public PrintOffice selectPrintOffice(int clientId);
	
	public Book selectBook(int bookId);
	
	public ArrayList<PrintCalc> selectPrintCalcList(Paging paging);
	
	public int updatePrintCalc(PrintCalc printCalc);
}
