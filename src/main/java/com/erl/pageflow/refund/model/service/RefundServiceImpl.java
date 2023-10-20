package com.erl.pageflow.refund.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.refund.model.dao.RefundDao;
import com.erl.pageflow.refund.model.vo.Refund;
import com.erl.pageflow.store.model.vo.Store;

@Service("refundservice")
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundDao refundDao;

	@Override
	public int selectGetListCount() {
		return refundDao.selectGetListCount();
	}

	@Override
	public ArrayList<Refund> selectRefundList(Paging paging) {
		return refundDao.selectRefundList(paging);
	}

	@Override
	public String selectRefundBookName(int bookId) {
		return refundDao.selectRefundBookName(bookId);
	}

	@Override
	public String selectRefundClientName(int clientId) {
		return refundDao.selectRefundClientName(clientId);
	}

	@Override
	public int selectRefundBookPrice(int bookId) {
		return refundDao.selectRefundBookPrice(bookId);
	}

	@Override
	public int selectRefundCountByDate(Search search) {
		return refundDao.selectRefundCountByDate(search);
	}

	@Override
	public ArrayList<Refund> selectRefundByDate(Search search) {
		return refundDao.selectRefundByDate(search);
	}

	@Override
	public int deleteInventory(int refundId) {
		return refundDao.deleteInventory(refundId);
	}

	@Override
	public int deleteRefund(int refundId) {
		return refundDao.deleteRefund(refundId);
	}

	@Override
	public int selectrefundCountByBookId(Search search) {
		return refundDao.selectrefundCountByBookId(search);
	}

	@Override
	public int selectrefundCountByBookName(Search search) {
		return refundDao.selectrefundCountByBookName(search);
	}

	@Override
	public int selectrefundCountByEmpName(Search search) {
		return refundDao.selectrefundCountByEmpName(search);
	}

	@Override
	public int selectrefundCountByClientName(Search search) {
		return refundDao.selectrefundCountByClientName(search);
	}

	@Override
	public ArrayList<Refund> selectrefundBybookId(Search search) {
		return refundDao.selectrefundBybookId(search);
	}

	@Override
	public ArrayList<Refund> selectrefundBybookName(Search search) {
		return refundDao.selectrefundBybookName(search);
	}

	@Override
	public ArrayList<Refund> selectrefundByEmpName(Search search) {
		return refundDao.selectrefundByEmpName(search);
	}

	@Override
	public ArrayList<Refund> selectrefundByClientName(Search search) {
		return refundDao.selectrefundByClientName(search);
	}

	@Override
	public int selectMaxRefundId() {
		return refundDao.selectMaxRefundId();
	}

	@Override
	public int selectPreInvenId() {
		return refundDao.selectPreInvenId();
	}

	@Override
	public int selectCurrInven() {
		return refundDao.selectCurrInven();
	}

	@Override
	public int insertRefund(Refund refund) {
		return refundDao.insertRefund(refund);
	}

	@Override
	public int insertInventory(Refund refund) {
		return refundDao.insertInventory(refund);
	}

}
