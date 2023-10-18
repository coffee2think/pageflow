package com.erl.pageflow.board.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.board.model.vo.BoardUpload;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

public interface BoardService {
	//업무게시판 게시글 리스트 갯수 조회
	public int selectBoardListCount();
	
	//업무게시판 게시글 리스트 조회
	public ArrayList<Board> selectBoardList(Paging paging);
	
	//업무게시판 게시글 조회
	public Board selectBoard(BoardKeyword boardKeyword);
	
	//업무게시판 게시글 등록
	public int insertBoard(Board board);
	
	//업무게시판 게시글 수정
	public int updateBoard(Board board);
	
	//업무게시판 게시글 삭제
	public int deletBoard(Board board);

	public int insertUploadBoard(BoardUpload boardUpload);

	public BoardUpload selectBoardListFile(BoardKeyword boardKeyword);

	public int updateBoardViewNum(BoardKeyword boardKeyword);

	public int updateUploadBoard(Board board);
	
	//----------------마이-----------------
	public int selectBoardListCountMy(BoardKeyword boardKeyword);

	public ArrayList<Board> selectBoardListMy(Search search);
	
	//----------------날짜-----------------
	public int selectBoardListDateCount(Search search);

	public ArrayList<Board> selectBoardListDate(Search search);
	
	//----------------서치-----------------
	public int selectBoardSearchCountContent(Search search);

	public int selectBoardSearchCountWriter(Search search);
	
	public int selectBoardSearchCountTitle(Search search);
	
	public ArrayList<Board> selectBoardSearchTitle(Search search);
	
	public ArrayList<Board> selectBoardSearchContent(Search search);
	
	public ArrayList<Board> selectBoardSearchWriter(Search search);

	public String selectDepName(int depId);
	
	
}
