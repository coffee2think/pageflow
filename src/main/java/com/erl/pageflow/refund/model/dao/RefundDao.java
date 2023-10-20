package com.erl.pageflow.refund.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.inventory.model.vo.Inventory;
import com.erl.pageflow.refund.model.vo.Refund;
import com.erl.pageflow.store.model.vo.Store;

@Repository("refundDao")
public class RefundDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectGetListCount() {
		return sqlSessionTemplate.selectOne("refundMapper.selectGetListCount");
	}

	public ArrayList<Refund> selectRefundList(Paging paging) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectRefundList", paging);
		return (ArrayList<Refund>) list;
	}

	public String selectRefundBookName(int bookId) {
		return sqlSessionTemplate.selectOne("refundMapper.selectRefundBookName", bookId);
	}

	public String selectRefundClientName(int clientId) {
		return sqlSessionTemplate.selectOne("refundMapper.selectRefundClientName", clientId);
	}

	public int selectRefundBookPrice(int bookId) {
		return sqlSessionTemplate.selectOne("refundMapper.selectRefundBookPrice", bookId);
	}

	public int selectRefundCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("refundMapper.selectRefundCountByDate", search);
	}

	public ArrayList<Refund> selectRefundByDate(Search search) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectRefundByDate", search);
		return (ArrayList<Refund>) list;
	}

	public int deleteRefund(int refundId) {
		return sqlSessionTemplate.delete("refundMapper.deleteRefund", refundId);
	}

	public int deleteInventory(int refundId) {
		return sqlSessionTemplate.delete("refundMapper.deleteInventory", refundId);
	}

	public int selectrefundCountByBookId(Search search) {
		return sqlSessionTemplate.selectOne("refundMapper.selectrefundCountByBookId", search);
	}

	public int selectrefundCountByBookName(Search search) {
		return sqlSessionTemplate.selectOne("refundMapper.selectrefundCountByBookName", search);
	}

	public int selectrefundCountByEmpName(Search search) {
		return sqlSessionTemplate.selectOne("refundMapper.selectrefundCountByEmpName", search);
	}

	public int selectrefundCountByClientName(Search search) {
		return sqlSessionTemplate.selectOne("refundMapper.selectrefundCountByClientName", search);
	}

	public ArrayList<Refund> selectrefundBybookId(Search search) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectrefundBybookId", search);
		return (ArrayList<Refund>) list;
	}

	public ArrayList<Refund> selectrefundBybookName(Search search) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectrefundBybookName", search);
		return (ArrayList<Refund>) list;
	}

	public ArrayList<Refund> selectrefundByEmpName(Search search) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectrefundByEmpName", search);
		return (ArrayList<Refund>) list;
	}

	public ArrayList<Refund> selectrefundByClientName(Search search) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectrefundByClientName", search);
		return (ArrayList<Refund>) list;
	}

	public int selectMaxRefundId() {
		return sqlSessionTemplate.selectOne("refundMapper.selectMaxRefundId");
	}

	public int selectPreInvenId() {
		return sqlSessionTemplate.selectOne("refundMapper.selectPreInvenId");
	}

	public int selectCurrInven() {
		return sqlSessionTemplate.selectOne("refundMapper.selectCurrInven");
	}
	
	public int insertRefund(Refund refund) {
		return sqlSessionTemplate.insert("refundMapper.insertRefund", refund);
	}

	public int insertInventory(Refund refund) {
		return sqlSessionTemplate.insert("refundMapper.insertInventory", refund);
	}

}
