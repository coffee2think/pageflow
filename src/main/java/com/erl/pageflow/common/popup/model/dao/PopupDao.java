package com.erl.pageflow.common.popup.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.BookWithStock;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.sales.model.vo.BookStore;
import com.erl.pageflow.sales.model.vo.PrintOffice;
import com.erl.pageflow.sales.model.vo.Storage;
import com.erl.pageflow.writer.model.vo.Writer;

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

	public ArrayList<BookWithStock> selectBookById(Search search) {
		List<BookWithStock> list = sqlSession.selectList("popupMapper.selectBookById", search);
		return (ArrayList<BookWithStock>) list;
	}

	public ArrayList<BookWithStock> selectBookByName(Search search) {
		List<BookWithStock> list = sqlSession.selectList("popupMapper.selectBookByName", search);
		return (ArrayList<BookWithStock>) list;
	}

	public int selectPrintOfficeCountById(int clientId) {
		return sqlSession.selectOne("popupMapper.selectPrintOfficeCountById", clientId);
	}

	public ArrayList<PrintOffice> selectPrintOfficeById(Search search) {
		List<PrintOffice> list = sqlSession.selectList("popupMapper.selectPrintOfficeById", search);
		return (ArrayList<PrintOffice>) list;
	}

	public int selectPrintOfficeCountByName(String clientName) {
		return sqlSession.selectOne("popupMapper.selectPrintOfficeCountByName", clientName);
	}

	public ArrayList<PrintOffice> selectPrintOfficeByName(Search search) {
		List<PrintOffice> list = sqlSession.selectList("popupMapper.selectPrintOfficeByName", search);
		return (ArrayList<PrintOffice>) list;
	}

	public int selectBookStoreCountById(int clientId) {
		return sqlSession.selectOne("popupMapper.selectBookStoreCountById", clientId);
	}

	public int selectBookStoreCountByName(String clientName) {
		return sqlSession.selectOne("popupMapper.selectBookStoreCountByName", clientName);
	}

	public ArrayList<BookStore> selectBookStoreById(Search search) {
		List<BookStore> list = sqlSession.selectList("popupMapper.selectBookStoreById", search);
		return (ArrayList<BookStore>) list;
	}

	public ArrayList<BookStore> selectBookStoreByName(Search search) {
		List<BookStore> list = sqlSession.selectList("popupMapper.selectBookStoreByName", search);
		return (ArrayList<BookStore>) list;
	}
	
	public int selectStorageCountById(int clientId) {
		return sqlSession.selectOne("popupMapper.selectStorageCountById", clientId);
	}

	public int selectStorageCountByName(String clientName) {
		return sqlSession.selectOne("popupMapper.selectStorageCountByName", clientName);
	}

	public ArrayList<Storage> selectStorageById(Search search) {
		List<Storage> list = sqlSession.selectList("popupMapper.selectStorageById", search);
		return (ArrayList<Storage>) list;
	}

	public ArrayList<Storage> selectStorageByName(Search search) {
		List<Storage> list = sqlSession.selectList("popupMapper.selectStorageByName", search);
		return (ArrayList<Storage>) list;
	}

	public int selectEmployeeCountByEmpName(String empName) {
		return sqlSession.selectOne("popupMapper.selectEmployeeCountByEmpName", empName);
	}

	public int selectEmployeeCountByDepName(String depName) {
		return sqlSession.selectOne("popupMapper.selectEmployeeCountByDepName", depName);
	}

	public ArrayList<Employee> selectEmployeeByEmpName(Search search) {
		List<Employee> list = sqlSession.selectList("popupMapper.selectEmployeeByEmpName", search);
		return (ArrayList<Employee>) list;
	}

	public ArrayList<Employee> selectEmployeeByDepName(Search search) {
		List<Employee> list = sqlSession.selectList("popupMapper.selectEmployeeByDepName", search);
		return (ArrayList<Employee>) list;
	}

	public int selectBookOrderCountByClientName(String clientName) {
		return sqlSession.selectOne("popupMapper.selectBookOrderCountByClientName", clientName);
	}

	public int selectBookOrderCountByDate(Search search) {
		return sqlSession.selectOne("popupMapper.selectBookOrderCountByDate", search);
	}

	public ArrayList<BookOrder> selectBookOrderByClientName(Search search) {
		List<BookOrder> list = sqlSession.selectList("popupMapper.selectBookOrderByClientName", search);
		return (ArrayList<BookOrder>) list;
	}

	public ArrayList<BookOrder> selectBookOrderByDate(Search search) {
		List<BookOrder> list = sqlSession.selectList("popupMapper.selectBookOrderByDate", search);
		return (ArrayList<BookOrder>) list;
	}

	public int selectWriterCountByName(String writerName) {
		return sqlSession.selectOne("popupMapper.selectWriterCountByName", writerName);
	}

	public ArrayList<Writer> selectWriterByName(Search search) {
		List<Writer> list = sqlSession.selectList("popupMapper.selectWriterByName", search);
		return (ArrayList<Writer>) list;
	}
	
}
