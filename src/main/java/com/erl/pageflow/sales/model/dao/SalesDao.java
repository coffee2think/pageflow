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
	private SqlSessionTemplate sqlSession;

	public int selectBookOrderCountByDate(Search search) {
		return sqlSession.selectOne("salesMapper.selectBookOrderCountByDate", search);
	}
	
	public ArrayList<BookOrder> selectBookOrderByDate(Search search) {
		List<BookOrder> list = sqlSession.selectList("salesMapper.selectBookOrderByDate", search);
		return (ArrayList<BookOrder>) list;
	}
	
	public int selectSalesCountByDate(Search search) {
		return sqlSession.selectOne("salesMapper.selectSalesCountByDate", search);
	}
	
	public ArrayList<Sales> selectSalesListByDate(Search search) {
		List<Sales> list = sqlSession.selectList("salesMapper.selectSalesListByDate", search);
		return (ArrayList<Sales>) list;
	}
	
	public int updateSales(Sales sales) {
		return sqlSession.update("salesMapper.updateSales", sales);
	}

	public BookForSales selectBook(int bookId) {
		return sqlSession.selectOne("salesMapper.selectBook", bookId);
	}

	public BookStore selectBookStore(int clientId) {
		return sqlSession.selectOne("salesMapper.selectBookStore", clientId);
	}

	public int selectClientListCount() {
		return sqlSession.selectOne("salesMapper.selectClientListCount");
	}
	
	public int selectClientCountByStartDate(Search search) {
		return sqlSession.selectOne("salesMapper.selectClientCountByStartDate", search);
	}

	public int selectClientCountByEndDate(Search search) {
		return sqlSession.selectOne("salesMapper.selectClientCountByEndDate", search);
	}
	
	public int selectClientCountByDate(Search search) {
		return sqlSession.selectOne("salesMapper.selectClientCountByDate", search);
	}

	public ArrayList<Client> selectClientList(Paging paging) {
		List<Client> list = sqlSession.selectList("salesMapper.selectClientList", paging);
		return (ArrayList<Client>) list;
	}
	
	public ArrayList<Client> selectClientByStartDate(Search search) {
		List<Client> list = sqlSession.selectList("salesMapper.selectClientByStartDate", search);
		return (ArrayList<Client>) list;
	}

	public ArrayList<Client> selectClientByEndDate(Search search) {
		List<Client> list = sqlSession.selectList("salesMapper.selectClientByEndDate", search);
		return (ArrayList<Client>) list;
	}

	public int insertBookOrder(BookOrder bookOrder) {
		return sqlSession.insert("salesMapper.insertBookOrder", bookOrder);
	}

	public int updateBookOrder(BookOrder bookOrder) {
		return sqlSession.update("salesMapper.updateBookOrder", bookOrder);
	}
	
	public int deleteBookOrder(int orderId) {
		return sqlSession.delete("salesMapper.deleteBookOrder", orderId);
	}

	public int insertClient(Client client) {
		return sqlSession.insert("salesMapper.insertClient", client);
	}

	public int updateClient(Client client) {
		return sqlSession.update("salesMapper.updateClient", client);
	}

	public int deleteClient(int clientId) {
		return sqlSession.delete("salesMapper.deleteClient", clientId);
	}

	public int selectBookOrderCountByBook(Search search) {
		return sqlSession.selectOne("salesMapper.selectBookOrderCountByBook", search);
	}

	public int selectBookOrderCountByBookStore(Search search) {
		return sqlSession.selectOne("salesMapper.selectBookOrderCountByBookStore", search);
	}

	public int selectBookOrderCountByLocation(Search search) {
		return sqlSession.selectOne("salesMapper.selectBookOrderCountByLocation", search);
	}

	public ArrayList<BookOrder> selectBookOrderByBook(Search search) {
		List<BookOrder> list = sqlSession.selectList("salesMapper.selectBookOrderByBook", search);
		return (ArrayList<BookOrder>) list;
	}

	public ArrayList<BookOrder> selectBookOrderByBookStore(Search search) {
		List<BookOrder> list = sqlSession.selectList("salesMapper.selectBookOrderByBookStore", search);
		return (ArrayList<BookOrder>) list;
	}

	public ArrayList<BookOrder> selectBookOrderByLocation(Search search) {
		List<BookOrder> list = sqlSession.selectList("salesMapper.selectBookOrderByLocation", search);
		return (ArrayList<BookOrder>) list;
	}

}
