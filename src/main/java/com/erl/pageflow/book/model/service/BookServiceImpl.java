package com.erl.pageflow.book.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.dao.BookDao;

@Service("bookService")
public class BookServiceImpl {
	@Autowired
	private BookDao bookDao;
}
