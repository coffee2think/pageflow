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
	public PrintOffice selectPrintOffice(int clientId) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOffice", clientId);
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
	
	//발주 수정
	public int updatePrintOrder(PrintOrder PrintOrder) {
		return sqlSessionTemplate.update("printMapper.updatePrintOrder", PrintOrder);
	}
	
	//
	public int selectMaxPrintOrderId() {
		return sqlSessionTemplate.selectOne("printMapper.selectMaxPrintOrderId");
	}
	
	//발주 등록
	public int insertPrintOrder(PrintOrder printOrder) {
		return sqlSessionTemplate.insert("printMapper.insertPrintOrder", printOrder);
	}
	
	//발주 삭제
	public int deletePrintOrder(int printOrder) {
		return sqlSessionTemplate.delete("printMapper.deletePrintOrder", printOrder);
	}
	
	public int selectPrintOrderCountByClientId(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOrderCountByClientId", search);
	}
	
	public int selectPrintOrderCountByPrintName(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOrderCountByPrintName", search);
	}
	
	public int selectPrintOrderCountByBookId(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOrderCountByBookId", search);
	}
	
	public int selectPrintOrderCountByBookName(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOrderCountByBookName", search);
	}
	
	public ArrayList<PrintOrder> selectPrintOrderByClientId(Search search){
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderByClientId", search);
		return (ArrayList<PrintOrder>) list;
	}
	
	public ArrayList<PrintOrder> selectPrintOrderByPrintName(Search search){
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderByPrintName", search);
		return (ArrayList<PrintOrder>) list;
	}
	
	public ArrayList<PrintOrder> selectPrintOrderByBookId(Search search){
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderByBookId", search);
		return (ArrayList<PrintOrder>) list;
	}
	
	public ArrayList<PrintOrder> selectPrintOrderByBookName(Search search){
		List<PrintOrder> list = sqlSessionTemplate.selectList("printMapper.selectPrintOrderByBookName", search);
		return (ArrayList<PrintOrder>) list;
	}
}





