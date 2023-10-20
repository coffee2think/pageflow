package com.erl.pageflow.refund.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.refund.model.vo.Refund;
import com.erl.pageflow.store.model.vo.Store;

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

	public int selectrefundCountByBookId(Search search);

	public int selectrefundCountByBookName(Search search);

	public int selectrefundCountByEmpName(Search search);

	public int selectrefundCountByClientName(Search search);

	public ArrayList<Refund> selectrefundBybookId(Search search);

	public ArrayList<Refund> selectrefundBybookName(Search search);

	public ArrayList<Refund> selectrefundByEmpName(Search search);

	public ArrayList<Refund> selectrefundByClientName(Search search);
	
	public int selectMaxRefundId();
	
	public int selectPreInvenId();

	public int selectCurrInven();
	
	public int insertRefund(Refund refund);

	public int insertInventory(Refund refund);
}
