package com.erl.pageflow.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.board.model.dao.BoardDao;
import com.erl.pageflow.board.model.vo.Board;
import com.erl.pageflow.common.BoardKeyword;
import com.erl.pageflow.common.Paging;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDao boardDao;

	@Override
	public int selectWorkListCount() {
		return boardDao.selectWorkListCount();
	}

	@Override
	public ArrayList<Board> selectWorkList(Paging paging) {
		return boardDao.selectWorkList(paging);
	}

	@Override
	public ArrayList<Board> selectFilterWorkList(Paging paging) {
		return boardDao.selectFilterWorkList(paging);
	}
	
	@Override
	public Board selectWork(BoardKeyword boardKeyword) {
		return boardDao.selectWork(boardKeyword);
	}

	@Override
	public int insertWork(Board board) {
		return boardDao.insertWork(board);
	}

	@Override
	public int updateWork(Board board) {
		return boardDao.updateWork(board);
	}

	@Override
	public int deletWork(Board board) {
		return boardDao.deletWork(board);
	}
	
}
