package com.erl.pageflow.reply.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Reply implements Serializable {
	
	private static final long serialVersionUID = 3670637553996244758L;
	private int replyId;
	private int depId;
	private int boardId;
	private int writerId;
	private int parent;
	private int depth;
	private String replyDetail;
	private Date createDate;
	private Date modifyDate;
	private Date deleteDate;
	
	public Reply() {}

	public Reply(int replyId, int depId, int boardId, int writerId, int parent, int depth, String replyDetail,
			Date createDate, Date modifyDate, Date deleteDate) {
		super();
		this.replyId = replyId;
		this.depId = depId;
		this.boardId = boardId;
		this.writerId = writerId;
		this.parent = parent;
		this.depth = depth;
		this.replyDetail = replyDetail;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public int getWriterId() {
		return writerId;
	}

	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getReplyDetail() {
		return replyDetail;
	}

	public void setReplyDetail(String replyDetail) {
		this.replyDetail = replyDetail;
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

	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", depId=" + depId + ", boardId=" + boardId + ", writerId=" + writerId
				+ ", parent=" + parent + ", depth=" + depth + ", replyDetail=" + replyDetail + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", deleteDate=" + deleteDate + "]";
	}
	
}
