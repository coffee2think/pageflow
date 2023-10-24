package com.erl.pageflow.approvalline.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
import com.erl.pageflow.approvalline.model.vo.ApprovalLineSave;
import com.erl.pageflow.common.ApprovalLineKeyword;
import com.erl.pageflow.common.Paging;

@Repository("approvalLineDao")
public class ApprovalLineDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<ApprovalLine> selectApprovalLineList(Paging paging){
		List<ApprovalLine> list = sqlSessionTemplate.selectList("approvalLineMapper.selectApprovalLineList", paging);
		return (ArrayList<ApprovalLine>)list;
	}
	
	public int insertApprovalLine(ApprovalLine approvalLine) {
		return sqlSessionTemplate.insert("approvalLineMapper.insertApprovalLine", approvalLine);
	}
	
	public int updateApprovalLine(ApprovalLine approvalLine) {
		return sqlSessionTemplate.update("approvalLineMapper.updateApprovalLine", approvalLine);
	}
	
	public int deleteApprovalLine(ApprovalLine approvalLine) {
		return sqlSessionTemplate.delete("approvalLineMapper.deleteApprovalLine", approvalLine);
	}

	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineList(int lineId) {
		List<ApprovalLineSave> list = sqlSessionTemplate.selectList("approvalLineMapper.selectMyApprovalSaveLineList", lineId);
		return (ArrayList<ApprovalLineSave>)list;
	}

	public ArrayList<ApprovalLine> selectApprovalLineList(int lineId) {
		List<ApprovalLine> list = sqlSessionTemplate.selectList("approvalLineMapper.selectApprovalLineList", lineId);
		return (ArrayList<ApprovalLine>)list;
	}

	public ArrayList<ApprovalLineSave> selectMyApprovalSaveLineListEmpId(int empId) {
		List<ApprovalLineSave> list = sqlSessionTemplate.selectList("approvalLineMapper.selectMyApprovalSaveLineListEmpId", empId);
		return (ArrayList<ApprovalLineSave>)list;
	}

	public int updateApprLineStampCheck(ApprovalLineKeyword approvalLineKeyword) {
		return sqlSessionTemplate.update("approvalLineMapper.updateApprLineStampCheck", approvalLineKeyword);
	}

	public int insertApprovalLineSave(ApprovalLineSave als) {
		return sqlSessionTemplate.insert("approvalLineMapper.insertApprovalLineSave", als);
	}

	public int selectApprovalSaveLineId() {
		return sqlSessionTemplate.insert("approvalLineMapper.selectApprovalSaveLineId");
	}

	
}
