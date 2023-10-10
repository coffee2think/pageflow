package com.erl.pageflow.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;

@Repository("boardDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//업무게시판 게시글 리스트 갯수 조회
	public int selectWorkListCount() {
		return sqlSessionTemplate.selectOne("boardMapper.selectWorkListCount");
	}
	
	//업무게시판 게시글 리스트 조회
	public ArrayList<Board> selectWorkList(Paging paging) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectWorkList", paging);
		return (ArrayList<Board>) list;
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	public ArrayList<Board> selectFilterWorkList(Paging paging) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectFilterWorkList", paging);
		return (ArrayList<Board>)list;
	}
	
	//업무게시판 게시글 조회
	public Board selectWork(BoardKeyword boardKeyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectWork", boardKeyword);
	}
	
	//업무게시판 게시글 등록
	public int insertWork(Board board) {
		return sqlSessionTemplate.insert("boardMapper.insertWork", board);
	}
	
	//업무게시판 게시글 수정
	public int updateWork(Board board) {
		return sqlSessionTemplate.insert("boardMapper.updateWork", board);
	}
	
	//업무게시판 게시글 삭제
	public int deletWork(Board board) {
		return sqlSessionTemplate.insert("boardMapper.deletWork", board);
	}
}
