package com.erl.pageflow.approvalline.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.approvalline.model.vo.ApprovalLine;
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

	public ApprovalLine selectMyApprovalLine(int lineId) {
		return sqlSessionTemplate.selectOne("approvalLineMapper.selectMyApprovalLine", lineId);
	}
	
	
}
