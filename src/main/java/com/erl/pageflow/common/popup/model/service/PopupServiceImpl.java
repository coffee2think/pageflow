package com.erl.pageflow.common.popup.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.popup.model.dao.PopupDao;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Service("popupService")
public class PopupServiceImpl implements PopupService {
	
	@Autowired
	private PopupDao popupDao;

	@Override
	public int selectBookCountById(int bookId) {
		return popupDao.selectBookCountById(bookId);
	}

	@Override
	public int selectBookCountByName(String bookName) {
		return popupDao.selectBookCountByName(bookName);
	}

	@Override
	public ArrayList<BookWithStock> selectBookById(int bookId) {
		return popupDao.selectBookById(bookId);
	}

	@Override
	public ArrayList<BookWithStock> selectBookByName(String bookName) {
		return popupDao.selectBookByName(bookName);
	}

	@Override
	public int selectPrintOfficeCountById(int clientId) {
		return popupDao.selectPrintOfficeCountById(clientId);
	}

	@Override
	public ArrayList<PrintOffice> selectPrintOfficeById(int clientId) {
		return popupDao.selectPrintOfficeById(clientId);
	}

	@Override
	public int selectPrintOfficeCountByName(String clientName) {
		return popupDao.selectPrintOfficeCountByName(clientName);
	}

	@Override
	public ArrayList<PrintOffice> selectPrintOfficeByName(String clientName) {
		return popupDao.selectPrintOfficeByName(clientName);
	}
	
	
	

}
