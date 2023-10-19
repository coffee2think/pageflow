package com.erl.pageflow.contract.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.contract.model.vo.Contract;

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
	
	// 계약 등록 계약번호 +1 처리
	public int selectMaxContrId() {
		return sqlSessionTemplate.selectOne("publishMapper.selectMaxContrId");
	}
}
