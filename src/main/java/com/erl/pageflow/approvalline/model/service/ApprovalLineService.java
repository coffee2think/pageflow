package com.erl.pageflow.approvalline.model.service;

import java.util.ArrayList;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.approvalline.model.vo.ApprovalLineSave;
import com.erl.pageflow.common.ApprovalLineKeyword;
import com.erl.pageflow.common.Paging;

public interface ApprovalLineService {
	
	public ArrayList<ApprovalLine> selectApprovalLineList(Paging paging);
	
	public int insertApprovalLine(ApprovalLine approvalLine);
	
	public int updateApprovalLine(ApprovalLine approvalLine);
	
	public int deleteApprovalLine(int savelineId);
	
	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineList(int lineId);

	public ArrayList<ApprovalLine> selectMyApprovalLineList(int lineId);

	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineListEmpId(int empId);

	public int updateApprLineStampCheck(ApprovalLineKeyword approvalLineKeyword);

	public int insertApprovalLineSave(ApprovalLineSave als);

	public int selectApprovalSaveLineId();

	public int selectApprovalLineId();

}
