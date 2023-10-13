package com.erl.pageflow.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 5421753500208939626L;
	private int depId;
	private int boardId;
	private int empId;
	private String boardTitle;
	private String boardDetail;
	private Date createDate;
	private Date modifyDate;
	private Date deleteDate;
	private int viewsNum;
	
	private String empName;
	private String depName;
	
	private int replyCount = 0;
	
	public Board() {}
	
	public Board(int depId, int boardId, int empId, String boardTitle, String boardDetail, Date createDate,
			Date modifyDate, Date deleteDate, int viewsNum, String empName, String depName) {
		super();
		this.depId = depId;
		this.boardId = boardId;
		this.empId = empId;
		this.boardTitle = boardTitle;
		this.boardDetail = boardDetail;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
		this.viewsNum = viewsNum;
		this.empName = empName;
		this.depName = depName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
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

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardDetail() {
		return boardDetail;
	}

	public void setBoardDetail(String boardDetail) {
		this.boardDetail = boardDetail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public int getViewsNum() {
		return viewsNum;
	}

	public void setViewsNum(int viewsNum) {
		this.viewsNum = viewsNum;
	}
	
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
	@Override
	public String toString() {
		return "Board [depId=" + depId + ", boardId=" + boardId + ", empId=" + empId + ", boardTitle=" + boardTitle
				+ ", boardDetail=" + boardDetail + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", deleteDate=" + deleteDate + ", viewsNum=" + viewsNum + ", empName=" + empName + ", depName="
				+ depName + ", replyCount=" + replyCount + "]";
	}

	
}
