package com.erl.pageflow.common.popup.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.PrintOffice;
import com.erl.pageflow.sales.model.vo.Storage;
import com.erl.pageflow.writer.model.vo.Writer;

public interface PopupService {

	int selectBookCountById(int bookId);

	int selectBookCountByName(String bookName);

	ArrayList<BookWithStock> selectBookById(Search search);

	ArrayList<BookWithStock> selectBookByName(Search search);

	int selectPrintOfficeCountById(int clientId);

	ArrayList<PrintOffice> selectPrintOfficeById(Search search);

	int selectPrintOfficeCountByName(String clientName);

	ArrayList<PrintOffice> selectPrintOfficeByName(Search search);

	int selectBookStoreCountById(int clientId);

	int selectBookStoreCountByName(String clientName);

	ArrayList<BookStore> selectBookStoreById(Search search);

	ArrayList<BookStore> selectBookStoreByName(Search search);

	int selectStorageCountById(int parseInt);

	int selectStorageCountByName(String keyword);

	ArrayList<Storage> selectStorageById(Search search);

	ArrayList<Storage> selectStorageByName(Search search);
	
	int selectEmployeeCountByEmpName(String keyword);

	int selectEmployeeCountByDepName(String keyword);

	ArrayList<Employee> selectEmployeeByEmpName(Search search);

	ArrayList<Employee> selectEmployeeByDepName(Search search);

	int selectBookOrderCountByClientName(String clientName);

	int selectBookOrderCountByDate(Search search);

	ArrayList<BookOrder> selectBookOrderByClientName(Search search);

	ArrayList<BookOrder> selectBookOrderByDate(Search search);

	int selectWriterCountByName(String writerName);

	ArrayList<Writer> selectWriterByName(Search search);

}
