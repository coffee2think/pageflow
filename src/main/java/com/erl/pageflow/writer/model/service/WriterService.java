package com.erl.pageflow.writer.model.service;

import java.util.ArrayList;

import com.erl.pageflow.writer.model.vo.Writer;
import com.erl.pageflow.common.Paging;

public interface WriterService {
	// 작가 리스트 개수 조회
	public int selectWriterListCount();
	
	// 작가 리스트 조회
	public ArrayList<Writer> selectWriterList(Paging paging);
	
	// 작가 필터링한 리스트 조회
	public ArrayList<Writer> selectWriterSearch(Paging paging);
	
	// 작가 조회
	public Writer selectWriter(String writerId);
	
	// 작가 등록
	public int insertWriter(Writer writer);
	
	// 작가 수정
	public int updateWriter(Writer writer);
	
	// 작가 삭제
	public int deleteWriter(String writerId);
}
