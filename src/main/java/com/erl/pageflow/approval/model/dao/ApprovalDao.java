package com.erl.pageflow.approval.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.approval.model.vo.Approval;
import com.erl.pageflow.common.Paging;

@Repository("approvalDao")
public class ApprovalDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListCount");
	}
	
	public ArrayList<Approval> selectApprovalList(Paging paging){
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalList", paging);
		return (ArrayList<Approval>)list;
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
	
}
