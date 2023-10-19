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

	public int selectApprovalListDateCount(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListDateCount", search);
	}

	public ArrayList<Approval> selectApprovalListDate(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListDate", search);
		return (ArrayList<Approval>)list;
	}
	
	public int selectApprovalListAllDateCount(Search search) {
		return sqlSessionTemplate.insert("approvalMapper.selectApprovalListAllDateCount", search);
	}

	public ArrayList<Approval> selectApprovalListAllDate(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListAllDate", search);
		return (ArrayList<Approval>)list;
	}

	
	
	
	public int selectApprSearchAllCountComplete(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountComplete", search);
	}

	public int selectApprSearchAllCountContinue(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountContinue", search);
	}

	public int selectApprSearchAllCountCompanion(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountCompanion", search);
	}

	public int selectApprSearchAllCountApprover(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountApprover", search);
	}

	public int selectApprSearchAllCountDrafter(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountDrafter", search);
	}

	public ArrayList<Approval> selectApprSearchAllComplete(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllComplete", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchAllContinue(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllContinue", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchAllCompanion(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllCompanion", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchAllApprover(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllApprover", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchAllDrafter(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllDrafter", search);
		return (ArrayList<Approval>)list;
	}

	public int selectApprSearchCountComplete(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountComplete", search);
	}

	public int selectApprSearchCountContinue(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountContinue", search);
	}

	public int selectApprSearchCountCompanion(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountCompanion", search);
	}

	public int selectApprSearchCountApprover(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountApprover", search);
	}

	public int selectApprSearchCountDrafter(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountDrafter", search);
	}

	public ArrayList<Approval> selectApprSearchComplete(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchComplete", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchContinue(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchContinue", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchCompanion(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchCompanion", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchApprover(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchApprover", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchDrafter(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchDrafter", search);
		return (ArrayList<Approval>)list;
	}
	
}
