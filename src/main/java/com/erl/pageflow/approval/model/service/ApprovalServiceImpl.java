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
	public ArrayList<Approval> selectApprovalListAll(Search search) {
		return approvalDao.selectApprovalListAll(search);
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
	public int selectApprSearchAllCountKeyword(Search search) {
		return approvalDao.selectApprSearchAllCountKeyword(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllKeyword(Search search) {
		return approvalDao.selectApprSearchAllKeyword(search);
	}

	@Override
	public int selectApprSearchCountKeyword(Search search) {
		return approvalDao.selectApprSearchCountKeyword(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchKeyword(Search search) {
		return approvalDao.selectApprSearchKeyword(search);
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
	public ArrayList<Approval> selectApprSearchAllApprover(Search search) {
		return approvalDao.selectApprSearchAllApprover(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchAllDrafter(Search search) {
		return approvalDao.selectApprSearchAllDrafter(search);
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
	public ArrayList<Approval> selectApprSearchApprover(Search search) {
		return approvalDao.selectApprSearchApprover(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchDrafter(Search search) {
		return approvalDao.selectApprSearchDrafter(search);
	}

	
	//ap
	@Override
	public int selectApprovalListCount_A(int empId) {
		return approvalDao.selectApprovalListCount_A(empId);
	}

	@Override
	public ArrayList<Approval> selectApprovalList_A(Search search) {
		return approvalDao.selectApprovalList_A(search);
	}

	@Override
	public int selectApprovalListDateCount_A(Search search) {
		return approvalDao.selectApprovalListDateCount_A(search);
	}

	@Override
	public ArrayList<Approval> selectApprovalListDate_A(Search search) {
		return approvalDao.selectApprovalListDate_A(search);
	}

	@Override
	public int selectApprSearchCountKeyword_A(Search search) {
		return approvalDao.selectApprSearchCountKeyword_A(search);
	}

	@Override
	public int selectApprSearchCountApprover_A(Search search) {
		return approvalDao.selectApprSearchCountApprover_A(search);
	}

	@Override
	public int selectApprSearchCountDrafter_A(Search search) {
		return approvalDao.selectApprSearchCountDrafter_A(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchKeyword_A(Search search) {
		return approvalDao.selectApprSearchKeyword_A(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchApprover_A(Search search) {
		return approvalDao.selectApprSearchApprover_A(search);
	}

	@Override
	public ArrayList<Approval> selectApprSearchDrafter_A(Search search) {
		return approvalDao.selectApprSearchDrafter_A(search);
	}

	@Override
	public Approval selectMyApproval(int approvalId) {
		return approvalDao.selectMyApproval(approvalId);
	}
	
	
}
