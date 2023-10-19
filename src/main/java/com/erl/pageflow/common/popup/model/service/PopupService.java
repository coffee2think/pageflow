package com.erl.pageflow.common.popup.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.BookWithStock;

public interface PopupService {

	int selectBookCountById(int bookId);

	int selectBookCountByName(String bookName);

	ArrayList<BookWithStock> selectBookById(int bookId);

	ArrayList<BookWithStock> selectBookByName(String bookName);

}
