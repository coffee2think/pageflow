package com.erl.pageflow.sales.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;

@Repository("salesDao")
public class SalesDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<BookOrder> selectBookOrderDate(Search search) {
		List<BookOrder> list = sqlSessionTemplate.selectList("salesMapper.selectBookOrderDate", search);
		return (ArrayList<BookOrder>) list;
	}

	public Book selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBook", bookId);
	}

	public BookStore selectBookStore(int clientId) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookStore", clientId);
	}
}
