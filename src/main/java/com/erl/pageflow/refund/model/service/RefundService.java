package com.erl.pageflow.refund.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.refund.model.vo.Refund;

public interface RefundService {

	public int selectGetListCount();
	
	public ArrayList<Refund> selectRefundList(Paging paging);
	
	public String selectRefundBookName(int bookId);
	
	public String selectRefundClientName(int clientId);
	
	public int selectRefundBookPrice(int bookId);
	
	public int selectRefundCountByDate(Search search);
	
	public ArrayList<Refund> selectRefundByDate(Search search);
	
	public int deleteRefund(int refundId);
	
	public int deleteInventory(int refundId);
}
