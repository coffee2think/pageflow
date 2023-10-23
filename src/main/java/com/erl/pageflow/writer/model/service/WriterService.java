package com.erl.pageflow.writer.model.service;

import java.util.ArrayList;

import com.erl.pageflow.writer.model.vo.Writer;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

public interface WriterService {
	// 작가 리스트 개수 조회
	public int selectWriterListCount();
	
	// 작가 리스트 조회
	public ArrayList<Writer> selectWriterList(Paging paging);
	
	// 작가 필터링한 리스트 조회
	public ArrayList<Writer> selectWriterSearch(Paging paging);
	
	// 작가 조회
	public Writer selectWriter(int writerId);

	// 작가 등록 작가번호 +1 처리
	public int selectMaxWriterId();
	
	// 작가 키워드 검색 개수 (작가명)
	public int selectWriterCountByWriter(Search search);
	
	// 작가 키워드 검색 (작가명)
	public ArrayList<Writer> selectWriterByWriter(Search search);
	
	// 작가 등록
	public int insertWriter(Writer writer);
	
	// 작가 수정
	public int updateWriter(Writer writer);
	
	// 작가 삭제
	public int deleteWriter(int writerId);
}
