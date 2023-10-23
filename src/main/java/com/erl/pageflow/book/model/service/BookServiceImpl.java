package com.erl.pageflow.book.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.book.model.dao.BookDao;
import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

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
	public int selectMaxBookId() {
		return bookDao.selectMaxBookId();
	}

	@Override
	public ArrayList<Book> selectBookByDate(Search search) {
		return bookDao.selectBookByDate(search);
	}

	@Override
	public int selectBookCountByDate(Search search) {
		return bookDao.selectBookCountByDate(search);
	}

	@Override
	public int selectBookCountByBook(Search search) {
		return bookDao.selectBookCountByBook(search);
	}

	@Override
	public ArrayList<Book> selectBookByBook(Search search) {
		return bookDao.selectBookByBook(search);
	}

	@Override
	public int selectBookCountByCategory(Search search) {
		return bookDao.selectBookCountByCategory(search);
	}

	@Override
	public ArrayList<Book> selectBookByCategory(Search search) {
		return bookDao.selectBookByCategory(search);
	}

	@Override
	public int selectBookCountByWriter(Search search) {
		return bookDao.selectBookCountByWriter(search);
	}

	@Override
	public ArrayList<Book> selectBookByWriter(Search search) {
		return bookDao.selectBookByWriter(search);
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
}
