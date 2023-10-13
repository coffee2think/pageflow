package com.erl.pageflow.approval.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.common.Paging;

public interface ApprovalService {
	
	public int selectListCount();
	
	public ArrayList<Approval> selectApprovalList(Paging paging);
	
	public ArrayList<Approval> selectApprovalListAll(Paging paging);
	
	public int insertApproval(Approval approval);
	
	public int updateApproval(Approval approval);
	
	public int deleteApproval(Approval approval);
}
