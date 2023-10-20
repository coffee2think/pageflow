package com.erl.pageflow.common.popup.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.sales.model.vo.PrintOffice;

@Repository("popupDao")
public class PopupDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int selectBookCountById(int bookId) {
		return sqlSession.selectOne("popupMapper.selectBookCountById", bookId);
	}

	public int selectBookCountByName(String bookName) {
		return sqlSession.selectOne("popupMapper.selectBookCountByName", bookName);
	}

	public ArrayList<BookWithStock> selectBookById(int bookId) {
		List<BookWithStock> list = sqlSession.selectList("popupMapper.selectBookById", bookId);
		return (ArrayList<BookWithStock>) list;
	}

	public ArrayList<BookWithStock> selectBookByName(String bookName) {
		List<BookWithStock> list = sqlSession.selectList("popupMapper.selectBookByName", bookName);
		return (ArrayList<BookWithStock>) list;
	}

	public int selectPrintOfficeCountById(int clientId) {
		return sqlSession.selectOne("popupMapper.selectPrintOfficeCountById", clientId);
	}

	public ArrayList<PrintOffice> selectPrintOfficeById(int clientId) {
		List<PrintOffice> list = sqlSession.selectList("popupMapper.selectPrintOfficeById", clientId);
		return (ArrayList<PrintOffice>) list;
	}

	public int selectPrintOfficeCountByName(String clientName) {
		return sqlSession.selectOne("popupMapper.selectPrintOfficeCountByName", clientName);
	}

	public ArrayList<PrintOffice> selectPrintOfficeByName(String clientName) {
		List<PrintOffice> list = sqlSession.selectList("popupMapper.selectPrintOfficeByName", clientName);
		return (ArrayList<PrintOffice>) list;
	}
	
}
