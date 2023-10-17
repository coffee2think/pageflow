package com.erl.pageflow.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.board.model.vo.BoardUpload;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;

@Repository("boardDao")
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//업무게시판 게시글 리스트 갯수 조회
	public int selectBoardListCount() {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardListCount");
	}
	
	//업무게시판 게시글 리스트 조회
	public ArrayList<Board> selectBoardList(Paging paging) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardList", paging);
		return (ArrayList<Board>) list;
	}
	
	//업무게시판 게시글 필터링된 리스트 조회
	public ArrayList<Board> selectBoardSearch(Paging paging) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectFilterBoardList", paging);
		return (ArrayList<Board>)list;
	}
	
	//업무게시판 게시글 조회
	public Board selectBoard(BoardKeyword boardKeyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoard", boardKeyword);
	}
	
	//업무게시판 게시글 등록
	public int insertBoard(Board board) {
		return sqlSessionTemplate.insert("boardMapper.insertBoard", board);
	}
	
	public BoardUpload selectBoardListFile(BoardKeyword boardKeyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardListFile", boardKeyword);
	}
	
	//업무게시판 게시글 삭제
	public int deletBoard(Board board) {
		return sqlSessionTemplate.delete("boardMapper.deletBoard", board);
	}

	public int insertUploadBoard(BoardUpload boardUpload) {
		return sqlSessionTemplate.insert("boardMapper.insertUploadBoard", boardUpload);
	}

	public int updateBoardViewNum(BoardKeyword boardKeyword) {
		return sqlSessionTemplate.insert("boardMapper.updateBoardViewNum", boardKeyword);
	}

	public int updateUploadBoard(Board board) {
		return sqlSessionTemplate.update("boardMapper.updateUploadBoard", board);
	}
	
	//업무게시판 게시글 수정
	public int updateBoard(Board board) {
		return sqlSessionTemplate.update("boardMapper.updateBoard", board);
	}

}
