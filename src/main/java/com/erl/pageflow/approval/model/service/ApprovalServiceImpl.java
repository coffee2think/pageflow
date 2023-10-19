package com.erl.pageflow.approval.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.approval.model.dao.ApprovalDao;
import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

@Service("approvalService")
public class ApprovalServiceImpl implements ApprovalService {
	
	@Autowired
	private ApprovalDao approvalDao;
	
	@Override
	public int selectApprovalListCount(int empId) {
		return approvalDao.selectApprovalListCount(empId);
	}

	@Override
	public ArrayList<Approval> selectApprovalList(Search search) {
		return approvalDao.selectApprovalList(search);
	}
	
	@Override
	public int selectApprovalListAllCount() {
		return approvalDao.selectApprovalListAllCount();
	}
	
	@Override
	public ArrayList<Approval> selectApprovalListAll(Paging paging) {
		return null;
	}

	@Override
	public int insertApproval(Approval approval) {
		return 0;
	}

	@Override
	public int updateApproval(Approval approval) {
		return 0;
	}

	@Override
	public int deleteApproval(Approval approval) {
		return 0;
	}

	@Override
	public Draft selectDraft(ApprovalKeyword approvalKeyword) {
		return approvalDao.selectDraft(approvalKeyword);
	}

	
}
