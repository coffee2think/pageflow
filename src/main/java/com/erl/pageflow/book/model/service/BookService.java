package com.erl.pageflow.book.model.service;

import java.util.ArrayList;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

public interface BookService {
	// 도서 리스트 개수 조회
	public int selectBookListCount();
	
	// 도서 리스트 조회
	public ArrayList<Book> selectBookList(Paging paging);
	
	// 도서 필터링한 리스트 조회
	public ArrayList<Book> selectBookSearch(Paging paging);
	
	// 도서 조회
	public Book selectBook(int bookId);
	
	// 도서 등록 도서번호 +1 처리
	public int selectMaxBookId();

	// 도서 날짜 검색
	public ArrayList<Book> selectBookByDate(Search search);
	
	// 도서 날짜 검색 카운트
	public int selectBookCountByDate(Search search);
	
	// 도서 키워드 검색 개수 (도서명)
	public int selectBookCountByBook(Search search);
	
	// 도서 키워드 검색 (도서명)
	public ArrayList<Book> selectBookByBook(Search search);
	
	// 도서 키워드 검색 개수 (카테고리)
	public int selectBookCountByCategory(Search search);
	
	// 도서 키워드 검색 (카테고리)
	public ArrayList<Book> selectBookByCategory(Search search);
	
	// 도서 키워드 검색 개수 (작가명)
	public int selectBookCountByWriter(Search search);
	
	// 도서 키워드 검색 (작가명)
	public ArrayList<Book> selectBookByWriter(Search search);
	
	// 도서 등록
	public int insertBook(Book book);
	
	// 도서 수정
	public int updateBook(Book book);
	
	// 도서 삭제
	public int deleteBook(int bookId);
}