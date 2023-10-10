package com.erl.pageflow.board.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;

public interface BoardService {
	//업무게시판 게시글 리스트 갯수 조회
	public int selectWorkListCount();
	
	//업무게시판 게시글 리스트 조회
	public ArrayList<Board> selectWorkList(Paging paging);
	
	//업무게시판 게시글 필터링된 리스트 조회
	public ArrayList<Board> selectFilterWorkList(Paging paging);
	
	//업무게시판 게시글 조회
	public Board selectWork(BoardKeyword boardKeyword);
	
	//업무게시판 게시글 등록
	public int insertWork(Board board);
	
	//업무게시판 게시글 수정
	public int updateWork(Board board);
	
	//업무게시판 게시글 삭제
	public int deletWork(Board board);
}
