package com.erl.pageflow.common.popup.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.common.popup.model.dao.PopupDao;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.PrintOffice;
import com.erl.pageflow.sales.model.vo.Storage;
import com.erl.pageflow.writer.model.vo.Writer;

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
	public ArrayList<BookWithStock> selectBookById(Search search) {
		return popupDao.selectBookById(search);
	}

	@Override
	public ArrayList<BookWithStock> selectBookByName(Search search) {
		return popupDao.selectBookByName(search);
	}

	@Override
	public int selectPrintOfficeCountById(int clientId) {
		return popupDao.selectPrintOfficeCountById(clientId);
	}

	@Override
	public ArrayList<PrintOffice> selectPrintOfficeById(Search search) {
		return popupDao.selectPrintOfficeById(search);
	}

	@Override
	public int selectPrintOfficeCountByName(String clientName) {
		return popupDao.selectPrintOfficeCountByName(clientName);
	}

	@Override
	public ArrayList<PrintOffice> selectPrintOfficeByName(Search search) {
		return popupDao.selectPrintOfficeByName(search);
	}

	@Override
	public int selectBookStoreCountById(int clientId) {
		return popupDao.selectBookStoreCountById(clientId);
	}

	@Override
	public int selectBookStoreCountByName(String clientName) {
		return popupDao.selectBookStoreCountByName(clientName);
	}

	@Override
	public ArrayList<BookStore> selectBookStoreById(Search search) {
		return popupDao.selectBookStoreById(search);
	}

	@Override
	public ArrayList<BookStore> selectBookStoreByName(Search search) {
		return popupDao.selectBookStoreByName(search);
	}
	
	@Override
	public int selectStorageCountById(int clientId) {
		return popupDao.selectStorageCountById(clientId);
	}

	@Override
	public int selectStorageCountByName(String clientName) {
		return popupDao.selectStorageCountByName(clientName);
	}

	@Override
	public ArrayList<Storage> selectStorageById(Search search) {
		return popupDao.selectStorageById(search);
	}

	@Override
	public ArrayList<Storage> selectStorageByName(Search search) {
		return popupDao.selectStorageByName(search);
	}

	@Override
	public int selectEmployeeCountByEmpName(String empName) {
		return popupDao.selectEmployeeCountByEmpName(empName);
	}

	@Override
	public int selectEmployeeCountByDepName(String depName) {
		return popupDao.selectEmployeeCountByDepName(depName);
	}

	@Override
	public ArrayList<Employee> selectEmployeeByEmpName(Search search) {
		return popupDao.selectEmployeeByEmpName(search);
	}

	@Override
	public ArrayList<Employee> selectEmployeeByDepName(Search search) {
		return popupDao.selectEmployeeByDepName(search);
	}

	@Override
	public int selectBookOrderCountByClientName(String clientName) {
		return popupDao.selectBookOrderCountByClientName(clientName);
	}

	@Override
	public int selectBookOrderCountByDate(Search search) {
		return popupDao.selectBookOrderCountByDate(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByClientName(Search search) {
		return popupDao.selectBookOrderByClientName(search);
	}

	@Override
	public ArrayList<BookOrder> selectBookOrderByDate(Search search) {
		return popupDao.selectBookOrderByDate(search);
	}

	@Override
	public int selectWriterCountByName(String writerName) {
		return popupDao.selectWriterCountByName(writerName);
	}

	@Override
	public ArrayList<Writer> selectWriterByName(Search search) {
		return popupDao.selectWriterByName(search);
	}

}
