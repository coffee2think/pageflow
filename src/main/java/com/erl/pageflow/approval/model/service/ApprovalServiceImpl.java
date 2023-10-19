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
	public int selectApprovalListDateCount(Search search) {
		return approvalDao.selectApprovalListDateCount(search);
	}
	
	@Override
	public ArrayList<Approval> selectApprovalListDate(Search search) {
		return approvalDao.selectApprovalListDate(search);
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

	@Override
	public int selectApprovalListAllDateCount(Search search) {
		return approvalDao.selectApprovalListAllDateCount(search);
	}

	@Override
	public ArrayList<Approval> selectApprovalListAllDate(Search search) {
		return approvalDao.selectApprovalListAllDate(search);
	}
	
	@Override
	public int selectApprSearchAllCountComplete(Search search) {
		return approvalDao.selectApprSearchAllCountComplete(search);
	}

	@Override
	public int selectApprSearchAllCountContinue(Search search) {
		return approvalDao.selectApprSearchAllCountContinue(search);
	}

	@Override
	public int selectApprSearchAllCountCompanion(Search search) {
		return approvalDao.selectApprSearchAllCountCompanion(search);
	}

	@Override
	public int selectApprSearchAllCountApprover(Search search) {
		return approvalDao.selectApprSearchAllCountApprover(search);
	}

	@Override
	public int selectApprSearchAllCountDrafter(Search search) {
		return approvalDao.selectApprSearchAllCountDrafter(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllComplete(Search search) {
		return approvalDao.selectApprSearchAllComplete(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllContinue(Search search) {
		return approvalDao.selectApprSearchAllContinue(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllCompanion(Search search) {
		return approvalDao.selectApprSearchAllCompanion(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllApprover(Search search) {
		return approvalDao.selectApprSearchAllApprover(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllDrafter(Search search) {
		return approvalDao.selectApprSearchAllDrafter(search);
	}

	
	
	@Override
	public int selectApprSearchCountComplete(Search search) {
		return approvalDao.selectApprSearchCountComplete(search);
	}

	@Override
	public int selectApprSearchCountContinue(Search search) {
		return approvalDao.selectApprSearchCountContinue(search);
	}

	@Override
	public int selectApprSearchCountCompanion(Search search) {
		return approvalDao.selectApprSearchCountCompanion(search);
	}

	@Override
	public int selectApprSearchCountApprover(Search search) {
		return approvalDao.selectApprSearchCountApprover(search);
	}

	@Override
	public int selectApprSearchCountDrafter(Search search) {
		return approvalDao.selectApprSearchCountDrafter(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchComplete(Search search) {
		return approvalDao.selectApprSearchComplete(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchContinue(Search search) {
		return approvalDao.selectApprSearchContinue(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchCompanion(Search search) {
		return approvalDao.selectApprSearchCompanion(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchApprover(Search search) {
		return approvalDao.selectApprSearchApprover(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchDrafter(Search search) {
		return approvalDao.selectApprSearchDrafter(search);
	}

	
	
	
}
