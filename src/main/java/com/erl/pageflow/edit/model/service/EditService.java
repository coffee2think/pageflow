package com.erl.pageflow.edit.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.edit.model.vo.Edit;

public interface EditService {
	// 편집 리스트 개수 조회
	public int selectEditListCount();
	
	// 편집 리스트 조회
	public ArrayList<Edit> selectEditList(Paging paging);
	
	// 편집 필터링한 리스트 조회
	public ArrayList<Edit> selectEditSearch(Paging paging);
	
	// 편집 조회
	public Edit selectEdit(int editId);

	// 편집 등록 편집번호 +1 처리
	public int selectMaxEditId();
	
	// 편집 날짜로 검색
	public ArrayList<Edit> selectEditBySDate(Search search);
	
	// 편집 날짜 카운트
	public int selectEditCountBySDate(Search search);
	
	// 편집 날짜로 검색
	public ArrayList<Edit> selectEditByEDate(Search search);
	
	// 편집 날짜 카운트
	public int selectEditCountByEDate(Search search);
	
	// 편집 키워드 검색 개수 (부서명)
	public int selectEditCountByDepartment(Search search);

	// 편집 키워드 검색 (부서명)
	public ArrayList<Edit> selectEditByDepartment(Search search);

	// 편집 키워드 검색 개수 (담당자명)
	public int selectEditCountByEmployee(Search search);

	// 편집 키워드 검색 (담당자명)
	public ArrayList<Edit> selectEditByEmployee(Search search);

	// 편집 키워드 검색 개수 (도서명)
	public int selectEditCountByBook(Search search);
	
	// 편집 키워드 검색 (도서명)
	public ArrayList<Edit> selectEditByBook(Search search);
	
	// 편집 등록
	public int insertEdit(Edit edit);
	
	// 편집 수정
	public int updateEdit(Edit edit);
	
	// 편집 삭제
	public int deleteEdit(Edit edit);
}