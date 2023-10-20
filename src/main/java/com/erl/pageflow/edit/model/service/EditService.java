package com.erl.pageflow.edit.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.edit.model.vo.Edit;
import com.erl.pageflow.sales.model.vo.BookOrder;
import com.erl.pageflow.writer.model.vo.Writer;

public interface EditService {
	// 편집 리스트 개수 조회
	public int selectEditListCount();
	
	// 편집 리스트 조회
	public ArrayList<Edit> selectEditList(Paging paging);
	
	// 편집 필터링한 리스트 조회
	public ArrayList<Edit> selectEditSearch(Paging paging);
	
	// 편집 조회
	public Edit selectEdit(int editId);
	
	// 편집 등록
	public int insertEdit(Edit edit);
	
	// 편집 수정
	public int updateEdit(Edit edit);
	
	// 편집 삭제
	public int deleteEdit(int editId);
	
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
}
