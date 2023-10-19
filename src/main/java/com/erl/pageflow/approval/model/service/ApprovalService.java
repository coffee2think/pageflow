package com.erl.pageflow.approval.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

public interface ApprovalService {
	
	public int selectApprovalListCount(int empId);
	
	public ArrayList<Approval> selectApprovalList(Search search);
	
	public int selectApprovalListAllCount();
	
	public ArrayList<Approval> selectApprovalListAll(Paging paging);
	
	public int insertApproval(Approval approval);
	
	public int updateApproval(Approval approval);
	
	public int deleteApproval(Approval approval);

	public Draft selectDraft(ApprovalKeyword approvalKeyword);

	
}
