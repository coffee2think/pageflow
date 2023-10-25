package com.erl.pageflow.approvalline.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approvalline.model.dao.ApprovalLineDao;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.approvalline.model.vo.ApprovalLineSave;
import com.erl.pageflow.common.ApprovalLineKeyword;
import com.erl.pageflow.common.Paging;

@Service("approvalLineService")
public class ApprovalLineServiceImpl implements ApprovalLineService{
	
	@Autowired
	private ApprovalLineDao approvalLineDao;

	@Override
	public ArrayList<ApprovalLine> selectApprovalLineList(Paging paging) {
		return approvalLineDao.selectApprovalLineList(paging);
	}

	@Override
	public int insertApprovalLine(ApprovalLine approvalLine) {
		return approvalLineDao.insertApprovalLine(approvalLine);
	}

	@Override
	public int updateApprovalLine(ApprovalLine approvalLine) {
		return approvalLineDao.updateApprovalLine(approvalLine);
	}

	@Override
	public int deleteApprovalLine(int savelineId) {
		return approvalLineDao.deleteApprovalLine(savelineId);
	}

	@Override
	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineList(int lineId) {
		return approvalLineDao.selectMyApprovalSaveLineList(lineId);
	}

	@Override
	public ArrayList<ApprovalLine> selectMyApprovalLineList(int lineId) {
		return approvalLineDao.selectMyApprovalLineList(lineId);
	}

	@Override
	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineListEmpId(int empId) {
		return approvalLineDao.selectMyApprovalSaveLineListEmpId(empId);
	}

	@Override
	public int updateApprLineStampCheck(ApprovalLineKeyword approvalLineKeyword) {
		return approvalLineDao.updateApprLineStampCheck(approvalLineKeyword);
	}

	@Override
	public int insertApprovalLineSave(ApprovalLineSave als) {
		return approvalLineDao.insertApprovalLineSave(als);
	}

	@Override
	public int selectApprovalSaveLineId() {
		return approvalLineDao.selectApprovalSaveLineId();
	}

	@Override
	public int selectApprovalLineId() {
		return approvalLineDao.selectApprovalLineId();
	}
	
	
	
}
