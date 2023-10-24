package com.erl.pageflow.contract.model.service;

import java.util.ArrayList;
import java.util.List;

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

	// 계약 등록 계약번호 +1 처리
	public int selectMaxContrId();
	
	// 계약 날짜 조회
	public ArrayList<Contract> selectContractByDate(Search search);
	
	// 계약 날짜 카운트
	public int selectContractCountByDate(Search search);
	
	// 계약 키워드 검색 개수 (책이름)
	public int selectContractCountByBook(Search search);
	
	// 계약 키워드 검색 (책이름)
	public ArrayList<Contract> selectContractByBook(Search search);
	
	// 계약 키워드 검색 개수 (카테고리)
	public int selectContractCountByCategory(Search search);
	
	// 계약 키워드 검색 (카테고리)
	public ArrayList<Contract> selectContractByCategory(Search search);
	
	// 계약 키워드 검색 개수 (작가명)
	public int selectContractCountByWriter(Search search);
	
	// 계약 키워드 검색 (작가명)
	public ArrayList<Contract> selectContractByWriter(Search search);
	
	// 계약 키워드 검색 개수 (담당자명)
	public int selectContractCountByEmployee(Search search);
	
	// 계약 키워드 검색 (담당자명)
	public ArrayList<Contract> selectContractByEmployee(Search search);
	
	// 계약 상태별(all) 검색 개수
	public int selectContractCountByStatusAll(Search search);
	
	// 계약 상태별(all) 검색
	public ArrayList<Contract> selectContractByStatusAll(Search search);
	
	// 계약 상태별(ing) 검색 개수
	public int selectContractCountByStatusIng(Search search);
	
	// 계약 상태별(ing) 검색
	public ArrayList<Contract> selectContractByStatusIng(Search search);
	
	// 계약 상태별(finish) 검색 개수
	public int selectContractCountByStatusFinish(Search search);
	
	// 계약 상태별(finish) 검색
	public ArrayList<Contract> selectContractByStatusFinish(Search search);
	
	// 계약 등록
	public int insertContract(Contract contract);
	
	// 계약 수정
	public int updateContract(Contract contract);
	
	// 계약 삭제
	public int deleteContract(int contrId);
}	