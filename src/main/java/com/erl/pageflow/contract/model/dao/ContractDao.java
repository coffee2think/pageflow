package com.erl.pageflow.contract.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.contract.model.vo.Contract;
import com.erl.pageflow.edit.model.vo.Edit;
import com.erl.pageflow.sales.model.vo.BookOrder;

@Repository("contractDao")
public class ContractDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 계약 리스트 개수 조회
	public int selectContractListCount() {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractListCount");
	}
	
	// 계약 리스트 조회
	public ArrayList<Contract> selectContractList(Paging paging) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractList", paging);
		return (ArrayList<Contract>)list;
	}
	
	// 계약 필터링한 리스트 조회
	public ArrayList<Contract> selectContractSearch(Paging paging) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractSearch", paging);
		return (ArrayList<Contract>)list;
	}
	
	// 계약 조회
	public Contract selectContract(int contrId) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContract", contrId);
	}
	
	// 계약 등록 계약번호 +1 처리
	public int selectMaxContrId() {
		return sqlSessionTemplate.selectOne("publishMapper.selectMaxContrId");
	}
	
	// 계약 날짜 검색
	public ArrayList<Contract> selectContractByDate(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByDate", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 날짜 검색 개수
	public int selectContractCountByDate(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByDate", search);
	}
	
	// 계약 키워드 검색 개수 (도서명)
	public int selectContractCountByBook(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByBook", search);
	}
	
	// 계약 키워드 검색 (도서명)
	public ArrayList<Contract> selectContractByBook(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByBook", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 키워드 검색 개수 (카테고리)
	public int selectContractCountByCategory(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByCategory", search);
	}
	
	// 계약 키워드 검색 (카테고리)
	public ArrayList<Contract> selectContractByCategory(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByCategory", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 키워드 검색 개수 (작가명)
	public int selectContractCountByWriter(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByWriter", search);
	}
	
	// 계약 키워드 검색 (작가명)
	public ArrayList<Contract> selectContractByWriter(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByWriter", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 키워드 검색 개수 (담당자명)
	public int selectContractCountByEmployee(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByEmployee", search);
	}
	
	// 계약 키워드 검색 (담당자명)
	public ArrayList<Contract> selectContractByEmployee(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByEmployee", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 상태별(all) 검색 개수
	public int selectContractCountByStatus(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByStatus", search);
	}
	
	// 계약 상태별(all) 검색
	public ArrayList<Contract> selectContractByStatus(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByStatus", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 상태별(all) 검색 개수
	public int selectContractCountByStatusAll(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByStatusAll", search);
	}
	
	// 계약 상태별(all) 검색
	public ArrayList<Contract> selectContractByStatusAll(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByStatusAll", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 상태별(ing) 검색 개수
	public int selectContractCountByStatusIng(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByStatusIng", search);
	}
	
	// 계약 상태별(ing) 검색
	public ArrayList<Contract> selectContractByStatusIng(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByStatusIng", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 상태별(finish) 검색 개수
	public int selectContractCountByStatusFinish(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectContractCountByStatusFinish", search);
	}
	
	// 계약 상태별(finish) 검색
	public ArrayList<Contract> selectContractByStatusFinish(Search search) {
		List<Contract> list = sqlSessionTemplate.selectList("publishMapper.selectContractByStatusFinish", search);
		return (ArrayList<Contract>) list;
	}
	
	// 계약 등록
	public int insertContract(Contract contract) {
		return sqlSessionTemplate.insert("publishMapper.insertContract", contract);
	}
	
	// 계약 수정
	public int updateContract(Contract contract) {
		return sqlSessionTemplate.update("publishMapper.updateContract", contract);
	}
	
	// 계약 삭제
	public int deleteContract(int contrId) {
		return sqlSessionTemplate.delete("publishMapper.deleteContract", contrId);
	}
}
