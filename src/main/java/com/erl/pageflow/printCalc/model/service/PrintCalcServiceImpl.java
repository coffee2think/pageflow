package com.erl.pageflow.printCalc.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printCalc.model.dao.PrintCalcDao;
import com.erl.pageflow.printCalc.model.vo.PrintCalc;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Service("printCalcService")
public class PrintCalcServiceImpl implements PrintCalcService{
	
	@Autowired
	private PrintCalcDao printCalcDao;
	
	@Override
	public int selectPrintCalcListCount() {
		return printCalcDao.selectPrintCalcListCount();
	}
	@Override
	public ArrayList<PrintCalc> selectPrintCalcByDate(Search search){
		return printCalcDao.selectPrintCalcByDate(search);
	}
	
	@Override
	public PrintOffice selectPrintOffice(int printId) {
		return printCalcDao.selectPrintOffice(printId);
	}
	
	@Override
	public Book selectBook(int bookId) {
		return printCalcDao.selectBook(bookId);
	}
	
	@Override
	public ArrayList<PrintCalc> selectPrintCalcList(Paging paging){
		return printCalcDao.selectPrintCalcList(paging);
	}
	
	@Override
	public int updatePrintCalc(PrintCalc printCalc) {
		return printCalcDao.updatePrintCalc(printCalc);
	}
}
