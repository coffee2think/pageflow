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
	
	public ArrayList<Approval> selectApprovalListAll(Search search){
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListAll", search);
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
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListAllDateCount", search);
	}

	public ArrayList<Approval> selectApprovalListAllDate(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListAllDate", search);
		return (ArrayList<Approval>)list;
	}

	
	
	
	public int selectApprSearchAllCountKeyword(Search search) {
		switch(search.getSearchType()) {
		case "complete":
			return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountComplete", search);
		case "continue":
			return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountContinue", search);
		case "companion":
			return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountCompanion", search);
		default:
			return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountAll", search);
	}
	}

	public ArrayList<Approval> selectApprSearchAllKeyword(Search search) {
		List<Approval> list = null;
		
		switch(search.getSearchType()) {
			case "complete":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllComplete", search);
				break;
			case "continue":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllContinue", search);
				break;
			case "companion":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllCompanion", search);
				break;
			default:
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllAll", search);
		}
		
		return (ArrayList<Approval>)list;
	}

	
	public int selectApprSearchCountKeyword_A(Search search) {
		switch(search.getSearchType()) {
			case "complete":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountComplete_A", search);
			case "continue":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountContinue_A", search);
			case "companion":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountCompanion_A", search);
			default:
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountAll_A", search);
		}
	}

	public ArrayList<Approval> selectApprSearchKeyword_A(Search search) {
		List<Approval> list = null;
		
		switch(search.getSearchType()) {
			case "complete":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchComplete_A", search);
				break;
			case "continue":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchContinue_A", search);
				break;
			case "companion":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchCompanion_A", search);
				break;
			default:
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAll_A", search);
		}
		
		return (ArrayList<Approval>)list;
	}
	
	public int selectApprSearchCountKeyword(Search search) {
		switch(search.getSearchType()) {
			case "complete":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountComplete", search);
			case "continue":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountContinue", search);
			case "companion":
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountCompanion", search);
			default:
				return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountAll", search);
		}
	}

	public ArrayList<Approval> selectApprSearchKeyword(Search search) {
		List<Approval> list = null;
		
		switch(search.getSearchType()) {
			case "complete":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchComplete", search);
				break;
			case "continue":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchContinue", search);
				break;
			case "companion":
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchCompanion", search);
				break;
			default:
				list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAll", search);
		}
		
		return (ArrayList<Approval>)list;
	}
	
	
	
	
	public int selectApprSearchAllCountApprover(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountApprover", search);
	}

	public int selectApprSearchAllCountDrafter(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchAllCountDrafter", search);
	}
	
	public ArrayList<Approval> selectApprSearchAllApprover(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllApprover", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchAllDrafter(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchAllDrafter", search);
		return (ArrayList<Approval>)list;
	}
	
	
	
	
	public int selectApprSearchCountApprover(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountApprover", search);
	}

	public int selectApprSearchCountDrafter(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountDrafter", search);
	}
	
	public ArrayList<Approval> selectApprSearchApprover(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchApprover", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchDrafter(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchDrafter", search);
		return (ArrayList<Approval>)list;
	}
	
	//ap
	public int selectApprovalListCount_A(int empId) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListCount_A", empId);
	}

	public ArrayList<Approval> selectApprovalList_A(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalList_A", search);
		return (ArrayList<Approval>)list;
	}

	public int selectApprovalListDateCount_A(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprovalListDateCount_A", search);
	}

	public ArrayList<Approval> selectApprovalListDate_A(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprovalListDate_A", search);
		return (ArrayList<Approval>)list;
	}
	
	public int selectApprSearchCountApprover_A(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountApprover_A", search);
	}

	public int selectApprSearchCountDrafter_A(Search search) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectApprSearchCountDrafter_A", search);
	}

	public ArrayList<Approval> selectApprSearchApprover_A(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchApprover_A", search);
		return (ArrayList<Approval>)list;
	}

	public ArrayList<Approval> selectApprSearchDrafter_A(Search search) {
		List<Approval> list = sqlSessionTemplate.selectList("approvalMapper.selectApprSearchDrafter_A", search);
		return (ArrayList<Approval>)list;
	}

	public Approval selectMyApproval(int approvalId) {
		return sqlSessionTemplate.selectOne("approvalMapper.selectMyApproval", approvalId);
	}
}
