package com.erl.pageflow.approval.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.approval.model.dao.ApprovalDao;
import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.common.Paging;

@Service("approvalService")
public class ApprovalServiceImpl implements ApprovalService {
	
	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public int selectListCount() {
		return 0;
	}

	@Override
	public ArrayList<Approval> selectApprovalList(Paging paging) {
		return null;
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

	
}
