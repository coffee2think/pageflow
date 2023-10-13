package com.erl.pageflow.notice.model.vo;

import java.util.Date;

public class Notice {
	
	private int noticeId;
	private int empid;
	private String noticeContent;
	private Date noticeCreateDate;
	private Date noticeModifyDate;
	private Date noticeDeleteDate;
	private String classify;
	private String importance;
	private String noticeOriginalFileName;
	private String noticeRenameFileName;
	private int noticeReadCount;
	
	public Notice() {
		
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}

	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}

	public Date getNoticeModifyDate() {
		return noticeModifyDate;
	}

	public void setNoticeModifyDate(Date noticeModifyDate) {
		this.noticeModifyDate = noticeModifyDate;
	}

	public Date getNoticeDeleteDate() {
		return noticeDeleteDate;
	}

	public void setNoticeDeleteDate(Date noticeDeleteDate) {
		this.noticeDeleteDate = noticeDeleteDate;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getNoticeOriginalFileName() {
		return noticeOriginalFileName;
	}

	public void setNoticeOriginalFileName(String noticeOriginalFileName) {
		this.noticeOriginalFileName = noticeOriginalFileName;
	}

	public String getNoticeRenameFileName() {
		return noticeRenameFileName;
	}

	public void setNoticeRenameFileName(String noticeRenameFileName) {
		this.noticeRenameFileName = noticeRenameFileName;
	}

	public int getNoticeReadCount() {
		return noticeReadCount;
	}

	public void setNoticeReadCount(int noticeReadCount) {
		this.noticeReadCount = noticeReadCount;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", empid=" + empid + ", noticeContent=" + noticeContent
				+ ", noticeCreateDate=" + noticeCreateDate + ", noticeModifyDate=" + noticeModifyDate
				+ ", noticeDeleteDate=" + noticeDeleteDate + ", classify=" + classify + ", importance=" + importance
				+ ", noticeOriginalFileName=" + noticeOriginalFileName + ", noticeRenameFileName="
				+ noticeRenameFileName + ", noticeReadCount=" + noticeReadCount + "]";
	}
	
	
	
	
	
}
