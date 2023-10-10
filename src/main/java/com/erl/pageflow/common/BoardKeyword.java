package com.erl.pageflow.common;

import java.io.Serializable;

public class BoardKeyword implements Serializable{
	
	private static final long serialVersionUID = 7692886790273006843L;
	private int empId;
	private int depId;
	private int boardId;
	
	public BoardKeyword(int empId, int depId) {
		super();
		this.empId = empId;
		this.depId = depId;
	}
	
	public BoardKeyword(int empId, int depId, int boardId) {
		super();
		this.empId = empId;
		this.depId = depId;
		this.boardId = boardId;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
		return "BoardKeyword [empId=" + empId + ", depId=" + depId + ", boardId=" + boardId + "]";
	}

	
	
}
