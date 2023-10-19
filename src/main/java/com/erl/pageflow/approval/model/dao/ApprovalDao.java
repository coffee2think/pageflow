package com.erl.pageflow.approval.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.approval.model.vo.Draft;
import com.erl.pageflow.common.ApprovalKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

@Repository("approvalDao")
public class ApprovalDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int selectApprovalListCount(int empId) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListCount", empId);
	}
	
	public ArrayList<Approval> selectApprovalList(Search search){
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalList", search);
		return (ArrayList<Approval>)list;
	}
	
	public int selectApprovalListAllCount() {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListAllCount");
	}
	
	public ArrayList<Approval> selectApprovalListAll(Paging paging){
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListAll", paging);
		return (ArrayList<Approval>)list;
	}
	
	public int insertApproval(Approval approval) {
		return sqlSessionTemplate.insert("approvalMapper.insertApproval", approval);
	}
	
	public int updateApproval(Approval approval) {
		return sqlSessionTemplate.update("approvalMapper.updateApproval", approval);
	}
	
	public int deleteApproval(Approval approval) {
		return sqlSessionTemplate.delete("approvalMapper.deleteApproval", approval);
	}

	public Draft selectDraft(ApprovalKeyword approvalKeyword) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectDraft", approvalKeyword);
	}

	
	
}
