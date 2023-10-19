package com.erl.pageflow.book.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.dao.BookDao;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	
	@Override
	public int selectBookListCount() {
		return bookDao.selectBookListCount();
	}
	
	@Override
	public ArrayList<Book> selectBookList(Paging paging) {
		return bookDao.selectBookList(paging);
	}
	
	@Override
	public ArrayList<Book> selectBookSearch(Paging paging) {
		return bookDao.selectBookSearch(paging);
	}
	
	@Override
	public Book selectBook(int bookId) {
		return bookDao.selectBook(bookId);
	}
	
	@Override
	public int insertBook(Book book) {
		return bookDao.insertBook(book);
	}
	
	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}
	
	@Override
	public int deleteBook(int bookId) {
		return bookDao.deleteBook(bookId);
	}
	
	@Override
	public int selectMaxBookId() {
		return bookDao.selectMaxBookId();
	}
}
