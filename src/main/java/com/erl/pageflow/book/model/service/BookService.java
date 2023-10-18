package com.erl.pageflow.book.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;

public interface BookService {
	// 도서 리스트 개수 조회
	public int selectBookListCount();
	
	// 도서 리스트 조회
	public ArrayList<Book> selectBookList(Paging paging);
	
	// 도서 필터링한 리스트 조회
	public ArrayList<Book> selectBookSearch(Paging paging);
	
	// 도서 조회
	public Book selectBook(int bookId);
	
	// 도서 등록
	public int insertBook(Book book);
	
	// 도서 수정
	public int updateBook(Book book);
	
	// 도서 삭제
	public int deleteBook(String bookId);
}
