package com.erl.pageflow.common;

import java.io.Serializable;

public class ReplyKeyword implements Serializable{
	
	private static final long serialVersionUID = 4153918486604318755L;
	private int depId;
	private int boardId;
	
	public ReplyKeyword(int depId, int boardId) {
		super();
		this.depId = depId;
		this.boardId = boardId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	@Override
	public String toString() {
		return "ReplyKeyword [depId=" + depId + ", boardId=" + boardId + "]";
	}
	
}
