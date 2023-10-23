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
	
	public ArrayList<Approval> selectApprovalListAll(Search search);
	
	public int insertApproval(Approval approval);
	
	public int updateApproval(Approval approval);
	
	public int deleteApproval(Approval approval);

	public Draft selectDraft(ApprovalKeyword approvalKeyword);

	public int selectApprovalListDateCount(Search search);

	public ArrayList<Approval> selectApprovalListDate(Search search);

	public int selectApprovalListAllDateCount(Search search);

	public ArrayList<Approval> selectApprovalListAllDate(Search search);

	
	public int selectApprSearchAllCountKeyword(Search search);

	public ArrayList<Approval> selectApprSearchAllKeyword(Search search);

	public int selectApprSearchCountKeyword(Search search);

	public ArrayList<Approval> selectApprSearchKeyword(Search search);

	public int selectApprSearchCountApprover(Search search);

	public int selectApprSearchAllCountApprover(Search search);

	public int selectApprSearchCountDrafter(Search search);

	public int selectApprSearchAllCountDrafter(Search search);

	public ArrayList<Approval> selectApprSearchApprover(Search search);

	public ArrayList<Approval> selectApprSearchAllApprover(Search search);
	
	public ArrayList<Approval> selectApprSearchDrafter(Search search);
	
	public ArrayList<Approval> selectApprSearchAllDrafter(Search search);
	
	//ap
	public int selectApprovalListCount_A(int empId);

	public ArrayList<Approval> selectApprovalList_A(Search search);

	public int selectApprovalListDateCount_A(Search search);

	public ArrayList<Approval> selectApprovalListDate_A(Search search);

	public int selectApprSearchCountKeyword_A(Search search);

	public int selectApprSearchCountApprover_A(Search search);

	public int selectApprSearchCountDrafter_A(Search search);

	public ArrayList<Approval> selectApprSearchKeyword_A(Search search);

	public ArrayList<Approval> selectApprSearchApprover_A(Search search);

	public ArrayList<Approval> selectApprSearchDrafter_A(Search search);

	public Approval selectMyApproval(int approvalId);

	public int updateApprovalState(ApprovalKeyword approvalKeyword);


}
