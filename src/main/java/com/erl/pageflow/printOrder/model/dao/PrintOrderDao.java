package com.erl.pageflow.printOrder.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Repository("printOrderDao")
public class PrintOrderDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//날짜로 조회
	public ArrayList<PrintOrder> selectPrintOrderByDate(Search search) {
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderByDate", search);
		return (ArrayList<PrintOrder>) list;
	}
	
	//거래처명 검색
	public PrintOffice selectPrintOffice(int printId) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOffice", printId);
	}
	
	//도서명 검색
	public Book selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("printMapper.selectBook",bookId);
	}
	
	//
	public int selectPrintOrderListCount() {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOrderListCount");
	}
	
	//
	public ArrayList<PrintOrder> selectPrintOrderList(Paging paging){
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderList", paging);
		return (ArrayList<PrintOrder>) list;
	}
	
	//발주 등록
	public int poinsert(PrintOrder printOrder) {
		return sqlSessionTemplate.insert("printMapper.poinsert", printOrder);
	}
}
