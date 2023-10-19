package com.erl.pageflow.common.popup.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.sales.model.vo.PrintOffice;

public interface PopupService {

	int selectBookCountById(int bookId);

	int selectBookCountByName(String bookName);

	ArrayList<BookWithStock> selectBookById(int bookId);

	ArrayList<BookWithStock> selectBookByName(String bookName);

	int selectPrintOfficeCountById(int clientId);

	ArrayList<PrintOffice> selectPrintOfficeById(int clientId);

	int selectPrintOfficeCountByName(String clientName);

	ArrayList<PrintOffice> selectPrintOfficeByName(String clientName);

}
