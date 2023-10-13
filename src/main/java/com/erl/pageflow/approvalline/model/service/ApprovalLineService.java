package com.erl.pageflow.approvalline.model.service;

import java.util.ArrayList;

import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.common.Paging;

public interface ApprovalLineService {
	
	public ArrayList<ApprovalLine> selectApprovalLineList(Paging paging);
	
	public int insertApprovalLine(ApprovalLine approvalLine);
	
	public int updateApprovalLine(ApprovalLine approvalLine);
	
	public int deleteApprovalLine(ApprovalLine approvalLine);
}
