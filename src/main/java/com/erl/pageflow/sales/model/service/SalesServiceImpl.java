package com.erl.pageflow.sales.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.dao.SalesDao;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;

@Service("salesService")
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	private SalesDao salesDao;

	@Override
	public ArrayList<BookOrder> selectBookOrderDate(Search search) {
		return salesDao.selectBookOrderDate(search);
	}

	@Override
	public Book selectBook(int bookId) {
		return salesDao.selectBook(bookId);
	}

	@Override
	public BookStore selectBookStore(int clientId) {
		return salesDao.selectBookStore(clientId);
	}
	
	
}
