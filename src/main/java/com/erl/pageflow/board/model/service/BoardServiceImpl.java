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
	public ArrayList<Board> selectBoardSearch(Paging paging) {
		return boardDao.selectBoardSearch(paging);
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
	public int deletBoard(Board board) {
		return boardDao.deletBoard(board);
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
	public int updateUploadBoard(Board board) {
		return boardDao.updateUploadBoard(board);
	}

	@Override
	public int selectBoardListNewCount(int duration) {
		return boardDao.selectBoardListNewCount(duration);
	}

	@Override
	public ArrayList<Board> selectBoardListDuration(Search search) {
		return boardDao.selectBoardListDuration(search);
	}
	
	
	
}
