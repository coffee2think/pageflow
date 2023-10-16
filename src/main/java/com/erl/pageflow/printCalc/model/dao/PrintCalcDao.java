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
	
	public PrintOffice selectPrintOffice(int printId) {
		return sqlSessionTemplate.selectOne("printMapper.selectPrintOffice", printId);
	}
	
	public Book selectBook(int bookId) {
		return sqlSessionTemplate.selectOne("printMapper.selectBook",bookId);
	}
	
	public ArrayList<PrintCalc> selectPrintCalcList(Paging paging){
		List<PrintCalc> list = sqlSessionTemplate.selectList("printMapper.selectPrintCalcList",paging);
		return (ArrayList<PrintCalc>) list;
	}
}
