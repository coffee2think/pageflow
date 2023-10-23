package com.erl.pageflow.book.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

@Repository("bookDao")
public class BookDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 도서 리스트 개수 조회
	public int selectBookListCount() {
		return sqlSessionTemplate.selectOne("publishMapper.selectBookListCount");
	}

	// 도서 리스트 조회
	public ArrayList<Book> selectBookList(Paging paging) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectBookList", paging);
		return (ArrayList<Book>) list;
	}

	// 도서 필터링한 리스트 조회
	public ArrayList<Book> selectBookSearch(Paging paging) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectFilterBookList", paging);
		return (ArrayList<Book>) list;
	}

	// 도서 조회
	public Book selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("publishMapper.selectBook", bookId);
	}

	// 도서 등록 도서번호 +1 처리
	public int selectMaxBookId() {
		return sqlSessionTemplate.selectOne("publishMapper.selectMaxBookId");
	}
	
	// 도서 날짜 검색
	public ArrayList<Book> selectBookByDate(Search search) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectBookByDate", search);
		return (ArrayList<Book>) list;
	}
	
	// 도서 날짜 검색 카운트
	public int selectBookCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectBookCountByDate", search);
	}
	
	// 도서 키워드 검색 개수 (도서명)
	public int selectBookCountByBook(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectBookCountByBook", search);
	}
	
	// 도서 키워드 검색 (도서명)
	public ArrayList<Book> selectBookByBook(Search search) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectBookByBook", search);
		return (ArrayList<Book>) list;
	}
	
	// 도서 키워드 검색 개수 (카테고리)
	public int selectBookCountByCategory(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectBookCountByCategory", search);
	}
	
	// 도서 키워드 검색 (카테고리)
	public ArrayList<Book> selectBookByCategory(Search search) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectBookByCategory", search);
		return (ArrayList<Book>) list;
	}
	
	// 도서 키워드 검색 개수 (작가명)
	public int selectBookCountByWriter(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectBookCountByWriter", search);
	}
	
	// 도서 키워드 검색 (작가명)
	public ArrayList<Book> selectBookByWriter(Search search) {
		List<Book> list = sqlSessionTemplate.selectList("publishMapper.selectBookByWriter", search);
		return (ArrayList<Book>) list;
	}
	
	// 도서 등록
	public int insertBook(Book book) {
		return sqlSessionTemplate.insert("publishMapper.insertBook", book);
	}
	
	// 도서 수정
	public int updateBook(Book book) {
		return sqlSessionTemplate.update("publishMapper.updateBook", book);
	}
	
	// 도서 삭제
	public int deleteBook(int bookId) {
		return sqlSessionTemplate.delete("publishMapper.deleteBook", bookId);
	}
}