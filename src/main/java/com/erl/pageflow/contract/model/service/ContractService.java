package com.erl.pageflow.contract.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.contract.model.vo.Contract;

public interface ContractService {
	// 계약 리스트 개수 조회
	public int selectContractListCount();
	
	// 계약 리스트 조회
	public ArrayList<Contract> selectContractList(Paging paging);
	
	// 계약 필터링한 리스트 조회
	public ArrayList<Contract> selectContractSearch(Paging paging);
	
	// 계약 조회
	public Contract selectContract(int contrId);
	
	// 계약 등록
	public int insertContract(Contract contract);
	
	// 계약 수정
	public int updateContract(Contract contract);
	
	// 계약 삭제
	public int deleteContract(int contrId);
	
	// 계약 등록 계약번호 +1 처리
	public int selectMaxContrId();
	
	// 계약 날짜 조회
	public ArrayList<Contract> selectContractByDate(Search search);
	
	// 계약 날짜 카운트
	public int selectContractCountByDate(Search search);
}	
