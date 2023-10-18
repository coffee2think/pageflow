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
import com.erl.pageflow.common.Search;

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
	
	//----------------마이-----------------
	public int selectBoardListCountMy(BoardKeyword boardKeyword) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardListCountMy", boardKeyword);
	}

	public ArrayList<Board> selectBoardListMy(Search search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardListMy", search);
		return (ArrayList<Board>)list;
	}
	
	//----------------날짜-----------------
	public int selectBoardListDateCount(Search search) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardListDateCount", search);
	}

	public ArrayList<Board> selectBoardListDate(Search search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardListDate", search);
		return (ArrayList<Board>)list;
	}
	
	//----------------서치-----------------
	public int selectBoardSearchCountTitle(Search search) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardSearchCountTitle", search);
	}
	
	public int selectBoardSearchCountContent(Search search) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardSearchCountContent", search);
	}
	
	public int selectBoardSearchCountWriter(Search search) {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardSearchCountWriter", search);
	}
	
	public ArrayList<Board> selectBoardSearchTitle(Search search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardSearchTitle", search);
		return (ArrayList<Board>)list;
	}
	
	public ArrayList<Board> selectBoardSearchContent(Search search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardSearchContent", search);
		return (ArrayList<Board>)list;
	}
	
	public ArrayList<Board> selectBoardSearchWriter(Search search) {
		List<Board> list = sqlSessionTemplate.selectList("boardMapper.selectBoardSearchWriter", search);
		return (ArrayList<Board>)list;
	}

	public String selectDepName(int depId) {
		return sqlSessionTemplate.selectOne("boardMapper.selectDepName", depId);
	}
	
	//업무게시판 게시글 삭제
	public int deleteBoard(Board board) {
		return sqlSessionTemplate.delete("boardMapper.deleteBoard", board);
	}

	public int selectBoardId() {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardId");
	}
	
}
