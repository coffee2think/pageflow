package com.erl.pageflow.refund.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.refund.model.dao.RefundDao;
import com.erl.pageflow.refund.model.vo.Refund;

@Service("refundservice")
public class RefundServiceImpl implements RefundService{
	
	@Autowired
	private RefundDao refundDao;
	
	@Override
	public int selectGetListCount() {
		return refundDao.selectGetListCount();
	}
	
	@Override
	public ArrayList<Refund> selectRefundList(Paging paging){
		return refundDao.selectRefundList(paging);
	}
}