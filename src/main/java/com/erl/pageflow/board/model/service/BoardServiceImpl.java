package com.erl.pageflow.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.board.model.dao.BoardDao;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.board.model.vo.BoardUpload;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;

	@Override
	public int selectBoardListCount() {
		return boardDao.selectBoardListCount();
	}

	@Override
	public ArrayList<Board> selectBoardList(Paging paging) {
		return boardDao.selectBoardList(paging);
	}

	@Override
	public Board selectBoard(BoardKeyword boardKeyword) {
		return boardDao.selectBoard(boardKeyword);
	}

	@Override
	public int insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}
	
	@Override
	public int insertUploadBoard(BoardUpload boardUpload) {
		return boardDao.insertUploadBoard(boardUpload);
	}

	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public BoardUpload selectBoardListFile(BoardKeyword boardKeyword) {
		return boardDao.selectBoardListFile(boardKeyword);
	}

	@Override
	public int updateBoardViewNum(BoardKeyword boardKeyword) {
		return boardDao.updateBoardViewNum(boardKeyword);
	}

	@Override
	public int updateUploadBoard(BoardUpload boardUpload) {
		return boardDao.updateUploadBoard(boardUpload);
	}
	
	//----------------마이-----------------
	@Override
	public int selectBoardListCountMy(BoardKeyword boardKeyword) {
		return boardDao.selectBoardListCountMy(boardKeyword);
	}

	@Override
	public ArrayList<Board> selectBoardListMy(Search search) {
		return boardDao.selectBoardListMy(search);
	}
	
	//----------------날짜-----------------
	@Override
	public int selectBoardListDateCount(Search search) {
		return boardDao.selectBoardListDateCount(search);
	}

	@Override
	public ArrayList<Board> selectBoardListDate(Search search) {
		return boardDao.selectBoardListDate(search);
	}
	
	//----------------서치-----------------
	@Override
	public int selectBoardSearchCountTitle(Search search) {
		return boardDao.selectBoardSearchCountTitle(search);
	}
	
	@Override
	public int selectBoardSearchCountContent(Search search) {
		return boardDao.selectBoardSearchCountContent(search);
	}
	
	@Override
	public int selectBoardSearchCountWriter(Search search) {
		return boardDao.selectBoardSearchCountWriter(search);
	}
	
	@Override
	public ArrayList<Board> selectBoardSearchTitle(Search search) {
		return boardDao.selectBoardSearchTitle(search);
	}
	
	@Override
	public ArrayList<Board> selectBoardSearchContent(Search search) {
		return boardDao.selectBoardSearchContent(search);
	}

	@Override
	public ArrayList<Board> selectBoardSearchWriter(Search search) {
		return boardDao.selectBoardSearchWriter(search);
	}

	@Override
	public String selectDepName(int depId) {
		return boardDao.selectDepName(depId);
	}

	@Override
	public int deleteBoard(Board board) {
		return boardDao.deleteBoard(board);
	}

	@Override
	public int selectBoardId() {
		return boardDao.selectBoardId();
	}

	@Override
	public int deleteBoardUpload(Board board) {
		return boardDao.deleteBoardUpload(board);
	}

	@Override
	public int selectBoardUpload(Board board) {
		return boardDao.selectBoardUpload(board);
	}
	
	
}
