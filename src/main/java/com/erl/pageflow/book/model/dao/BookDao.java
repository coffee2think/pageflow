package com.erl.pageflow.book.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;

@Repository("bookDao")
public class BookDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 도서 리스트 개수 조회
	public int selectBookListCount() {
		return sqlSessionTemplate.selectOne("bookMapper.selectBookListCount");
	}

	// 도서 리스트 조회
	public ArrayList<Book> selectBookList(Paging paging) {
		List<Book> list = sqlSessionTemplate.selectList("bookMapper.selectBookList", paging);
		return (ArrayList<Book>) list;
	}

	// 도서 필터링한 리스트 조회
	public ArrayList<Book> selectBookSearch(Paging paging) {
		List<Book> list = sqlSessionTemplate.selectList("bookMapper.selectFilterBookList", paging);
		return (ArrayList<Book>) list;
	}

	// 도서 조회
	public Book selectBook(String bookId) {
		return sqlSessionTemplate.selectOne("bookMapper.selectBook", bookId);
	}
	
	// 도서 등록
	public int insertBook(Book book) {
		return sqlSessionTemplate.insert("bookMapper.insertBook", book);
	}
	
	// 도서 수정
	public int updateBook(Book book) {
		return sqlSessionTemplate.update("bookMapper.updateBook", book);
	}
	
	// 도서 삭제
	public int deleteBook(String bookId) {
		return sqlSessionTemplate.delete("bookMapper.deleteBook", bookId);
	}
}
