package com.erl.pageflow.edit.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.edit.model.vo.Edit;

@Repository("editDao")
public class EditDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 편집 리스트 개수 조회
	public int selectEditListCount() {
		return sqlSessionTemplate.selectOne("publishMapper.selectEditListCount");
	}
	
	// 편집 리스트 조회
	public ArrayList<Edit> selectEditList(Paging paging) {
		List<Edit> list = sqlSessionTemplate.selectList("publishMapper.selectEditList", paging);
		return (ArrayList<Edit>) list;
	}
	
	// 편집 필터링한 리스트 조회
	public ArrayList<Edit> selectEditSearch(Paging paging) {
		List<Edit> list = sqlSessionTemplate.selectList("publishMapper.selectEditSearch", paging);
		return (ArrayList<Edit>) list;
	}
	
	// 편집 조회
	public Edit selectEdit(int editId) {
		return sqlSessionTemplate.selectOne("publishMapper.selectEdit", editId);
	}
	
	// 편집 등록
	public int insertEdit(Edit edit) {
		return sqlSessionTemplate.insert("publishMapper.insertEdit", edit);
	}
	
	// 편집 수정
	public int updateEdit(Edit edit) {
		return sqlSessionTemplate.update("publishMapper.updateEdit", edit);
	}
	
	// 편집 삭제
	public int deleteEdit(int editId) {
		return sqlSessionTemplate.delete("publishMapper.deleteEdit", editId);
	}
	
	// 편집 등록 편집번호 +1 처리
	public int selectMaxEditId() {
		return sqlSessionTemplate.selectOne("publishMapper.selectMaxEditId");
	}
	
	// 편집 시작날짜 검색
	public ArrayList<Edit> selectEditBySDate(Search search) {
		List<Edit> list = sqlSessionTemplate.selectList("publishMapper.selectEditBySDate", search);
		return (ArrayList<Edit>) list;
	}
	
	// 편집 시작날짜 검색 개수
	public int selectEditCountBySDate(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectEditCountBySDate", search);
	}
	
	// 편집 마감날짜 검색
	public ArrayList<Edit> selectEditByEDate(Search search) {
		List<Edit> list = sqlSessionTemplate.selectList("publishMapper.selectEditByEDate", search);
		return (ArrayList<Edit>) list;
	}
	
	// 편집 마감날짜 검색 개수
	public int selectEditCountByEDate(Search search) {
		return sqlSessionTemplate.selectOne("publishMapper.selectEditCountByEDate", search);
	}
}
