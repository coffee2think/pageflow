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

	public int selectApprovalListDateCount(Search search);

	public ArrayList<Approval> selectApprovalListDate(Search search);

	public int selectApprovalListAllDateCount(Search search);

	public ArrayList<Approval> selectApprovalListAllDate(Search search);

	
	public int selectApprSearchAllCountComplete(Search search);

	public int selectApprSearchAllCountContinue(Search search);

	public int selectApprSearchAllCountCompanion(Search search);
	
	public int selectApprSearchAllCountApprover(Search search);
	
	public int selectApprSearchAllCountDrafter(Search search);
	
	public ArrayList<Approval> selectApprSearchAllComplete(Search search);

	public ArrayList<Approval> selectApprSearchAllContinue(Search search);

	public ArrayList<Approval> selectApprSearchAllCompanion(Search search);

	public ArrayList<Approval> selectApprSearchAllApprover(Search search);
	
	public ArrayList<Approval> selectApprSearchAllDrafter(Search search);

	public int selectApprSearchCountComplete(Search search);

	public int selectApprSearchCountContinue(Search search);

	public int selectApprSearchCountCompanion(Search search);

	public int selectApprSearchCountApprover(Search search);

	public int selectApprSearchCountDrafter(Search search);

	public ArrayList<Approval> selectApprSearchComplete(Search search);

	public ArrayList<Approval> selectApprSearchContinue(Search search);

	public ArrayList<Approval> selectApprSearchCompanion(Search search);

	public ArrayList<Approval> selectApprSearchApprover(Search search);

	public ArrayList<Approval> selectApprSearchDrafter(Search search);
}
