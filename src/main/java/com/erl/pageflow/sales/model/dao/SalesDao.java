package com.erl.pageflow.sales.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.sales.model.vo.BookForSales;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.Client;
import com.erl.pageflow.sales.model.vo.Sales;

@Repository("salesDao")
public class SalesDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectBookOrderCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookOrderCountByDate", search);
	}
	
	public ArrayList<BookOrder> selectBookOrderByDate(Search search) {
		List<BookOrder> list = sqlSessionTemplate.selectList("salesMapper.selectBookOrderByDate", search);
		return (ArrayList<BookOrder>) list;
	}
	
	public int selectSalesCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("salesMapper.selectSalesCountByDate", search);
	}
	
	public ArrayList<Sales> selectSalesListByDate(Search search) {
		List<Sales> list = sqlSessionTemplate.selectList("salesMapper.selectSalesListByDate", search);
		return (ArrayList<Sales>) list;
	}

	public BookForSales selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBook", bookId);
	}

	public BookStore selectBookStore(int clientId) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookStore", clientId);
	}

	public int selectClientListCount() {
		return sqlSessionTemplate.selectOne("salesMapper.selectClientListCount");
	}

	public ArrayList<Client> selectClientList(Paging paging) {
		List<Client> list = sqlSessionTemplate.selectList("salesMapper.selectClientList", paging);
		return (ArrayList<Client>) list;
	}

	public int insertBookOrder(BookOrder bookOrder) {
		return sqlSessionTemplate.insert("salesMapper.insertBookOrder", bookOrder);
	}

	public int updateBookOrder(BookOrder bookOrder) {
		return sqlSessionTemplate.update("salesMapper.updateBookOrder", bookOrder);
	}
	
	public int deleteBookOrder(int tradeId) {
		return sqlSessionTemplate.delete("salesMapper.deleteBookOrder", tradeId);
	}

	public int insertClient(Client client) {
		return sqlSessionTemplate.insert("salesMapper.insertClient", client);
	}

	public int updateClient(Client client) {
		return sqlSessionTemplate.update("salesMapper.updateClient", client);
	}

	public int deleteClient(int clientId) {
		return sqlSessionTemplate.delete("salesMapper.deleteClient", clientId);
	}

	public int selectBookOrderCountByBook(Search search) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookOrderCountByBook", search);
	}

	public int selectBookOrderCountByBookStore(Search search) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookOrderCountByBookStore", search);
	}

	public int selectBookOrderCountByLocation(Search search) {
		return sqlSessionTemplate.selectOne("salesMapper.selectBookOrderCountByLocation", search);
	}

	public ArrayList<BookOrder> selectBookOrderByBook(Search search) {
		List<BookOrder> list = sqlSessionTemplate.selectList("salesMapper.selectBookOrderByBook", search);
		return (ArrayList<BookOrder>) list;
	}

	public ArrayList<BookOrder> selectBookOrderByBookStore(Search search) {
		List<BookOrder> list = sqlSessionTemplate.selectList("salesMapper.selectBookOrderByBookStore", search);
		return (ArrayList<BookOrder>) list;
	}

	public ArrayList<BookOrder> selectBookOrderByLocation(Search search) {
		List<BookOrder> list = sqlSessionTemplate.selectList("salesMapper.selectBookOrderByLocation", search);
		return (ArrayList<BookOrder>) list;
	}
	
}
