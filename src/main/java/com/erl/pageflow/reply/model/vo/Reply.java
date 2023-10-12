package com.erl.pageflow.reply.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Reply implements Serializable {
	
	private static final long serialVersionUID = 3670637553996244758L;
	private int replyId;
	private int depId;
	private int boardId;
	private int empId;
	private int bundleId;
	private int bundleId2;
	private int parentId;
	private int depth;
	private int depth2;
	private String replyDetail;
	private Date createDate;
	private Date modifyDate;
	private Date deleteDate;
	private String empName;
	private String parentEmpName;
	
	public Reply(int replyId, int depId, int boardId, int empId, int bundleId, int bundleId2, int parentId, int depth,
			int depth2, String replyDetail, Date createDate, Date modifyDate, Date deleteDate, String empName) {
		super();
		this.replyId = replyId;
		this.depId = depId;
		this.boardId = boardId;
		this.empId = empId;
		this.bundleId = bundleId;
		this.bundleId2 = bundleId2;
		this.parentId = parentId;
		this.depth = depth;
		this.depth2 = depth2;
		this.replyDetail = replyDetail;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.deleteDate = deleteDate;
		this.empName = empName;
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
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getBundleId() {
		return bundleId;
	}
	public void setBundleId(int bundleId) {
		this.bundleId = bundleId;
	}
	public int getBundleId2() {
		return bundleId2;
	}
	public void setBundleId2(int bundleId2) {
		this.bundleId2 = bundleId2;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getDepth2() {
		return depth2;
	}
	public void setDepth2(int depth2) {
		this.depth2 = depth2;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getParentEmpName() {
		return parentEmpName;
	}
	public void setParentEmpName(String parentEmpName) {
		this.parentEmpName = parentEmpName;
	}
	
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", depId=" + depId + ", boardId=" + boardId + ", empId=" + empId
				+ ", bundleId=" + bundleId + ", bundleId2=" + bundleId2 + ", parentId=" + parentId + ", depth=" + depth
				+ ", depth2=" + depth2 + ", replyDetail=" + replyDetail + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", deleteDate=" + deleteDate + ", empName=" + empName + ", parentEmpName="
				+ parentEmpName + "]";
	}
	
}
