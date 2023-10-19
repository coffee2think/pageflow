package com.erl.pageflow.writer.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.book.model.vo.Book;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.writer.model.vo.Writer;

@Repository("writerDao")
public class WriterDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 작가 리스트 개수 조회
	public int selectWriterListCount(){
		return sqlSessionTemplate.selectOne("publishMapper.selectWriterListCount");
	}
	// 작가 리스트 조회
	public ArrayList<Writer> selectWriterList(Paging paging){
		List<Writer> list = sqlSessionTemplate.selectList("publishMapper.selectWriterList", paging);
		return (ArrayList<Writer>) list;
	}
	
	// 작가 필터링한 리스트 조회
	public ArrayList<Writer> selectWriterSearch(Paging paging){
		List<Writer> list = sqlSessionTemplate.selectList("publishMapper.selectWriterSearch", paging);
		return (ArrayList<Writer>) list;
	}
	
	// 작가 조회
	public Writer selectWriter(int writerId) {
		return sqlSessionTemplate.selectOne("publishMapper.selectWriter", writerId);
	}
	
	// 작가 등록
	public int insertWriter(Writer writer) {
		return sqlSessionTemplate.insert("publishMapper.insertWriter", writer);
	}
	
	// 작가 수정
	public int updateWriter(Writer writer) {
		return sqlSessionTemplate.update("publishMapper.updateWriter", writer);
	}
	
	// 작가 삭제
	public int deleteWriter(int writerId) {
		return sqlSessionTemplate.delete("publishMapper.deleteWriter", writerId);
	}
	
	// 작가 등록 작가번호 +1 처리
	public int selectMaxWriterId() {
		return sqlSessionTemplate.selectOne("publishMapper.selectMaxWriterId");
	}
}
