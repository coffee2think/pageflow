package com.erl.pageflow.approvalline.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.approvalline.model.dao.ApprovalLineDao;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.common.Paging;

@Service("approvalLineService")
public class ApprovalLineServiceImpl implements ApprovalLineService{
	
	@Autowired
	private ApprovalLineDao approvalLineDao;

	@Override
	public ArrayList<ApprovalLine> selectApprovalLineList(Paging paging) {
		return null;
	}

	@Override
	public int insertApprovalLine(ApprovalLine approvalLine) {
		return 0;
	}

	@Override
	public int updateApprovalLine(ApprovalLine approvalLine) {
		return 0;
	}

	@Override
	public int deleteApprovalLine(ApprovalLine approvalLine) {
		return 0;
	}

	@Override
	public ApprovalLine selectMyApprovalLine(int lineId) {
		return approvalLineDao.selectMyApprovalLine(lineId);
	}
	
	
}
