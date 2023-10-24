package com.erl.pageflow.printCalc.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.printCalc.model.vo.PrintCalc;
import com.erl.pageflow.printOrder.model.vo.PrintOrder;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Repository("printCalcDao")
public class PrintCalcDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int selectPrintCalcListCount() {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintCalcListCount");
	}
	
	public ArrayList<PrintCalc> selectPrintCalcByDate(Search search){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcByDate", search);
		return (ArrayList<PrintCalc>) list;
	}
	
	public PrintOffice selectPrintOffice(int clientId) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOffice", clientId);
	}
	
	public Book selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("printMapper.selectBook",bookId);
	}
	
	public ArrayList<PrintCalc> selectPrintCalcList(Paging paging){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcList",paging);
		return (ArrayList<PrintCalc>) list;
	}
	
	public int updatePrintCalc(PrintCalc printCalc) {
		return sqlSessionTemplate.update("printMapper.updatePrintCalc", printCalc);
	}
	
	public int selectPrintCalcCountByOrderId(int search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintCalcCountByOrderId", search);
	}
	
	public int selectPrintCalcCountByPrintName(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintCalcCountByPrintName", search);
	}
	
	public int selectPrintCalcCountBookId(int search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintCalcCountBookId", search);
	}
	
	public int selectPrintCalcCountBookName(Search search) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintCalcCountBookName", search);
	}
	
	public ArrayList<PrintCalc> selectPrintCalcByOrderId(int search){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcByOrderId", search);
		return (ArrayList<PrintCalc>) list;
	}
	
	public ArrayList<PrintCalc> selectPrintCalcByPrintName(Search search){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcByPrintName", search);
		return (ArrayList<PrintCalc>) list;
	}
	
	public ArrayList<PrintCalc> selectPrintCalcByBookId(int search){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcByBookId", search);
		return (ArrayList<PrintCalc>) list;
	}
	
	public ArrayList<PrintCalc> selectPrintCalcByBookName(Search search){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcByBookName", search);
		return (ArrayList<PrintCalc>) list;
	}
}
