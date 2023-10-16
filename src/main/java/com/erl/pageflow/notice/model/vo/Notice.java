package com.erl.pageflow.notice.model.vo;

import java.util.Date;

public class Notice {
	
	private int noticeId;
	private int empId;
	private String empName;
	private String noticeTitle;
	private String noticeDetail;
	private Date noticeCreateDate;
	private Date noticeModifyDate;
	private Date noticeDeleteDate;
	private String classify;
	private String importance;
	private int noticeReadCount;
	private String noticeOriginalFileName;
	private String noticeRenameFileName;
	
	public Notice() {
		super();
	}

	public Notice(int noticeId, int empId, String empName, String noticeTitle, String noticeDetail,
			Date noticeCreateDate, Date noticeModifyDate, Date noticeDeleteDate, String classify, String importance,
			int noticeReadCount, String noticeOriginalFileName, String noticeRenameFileName) {
		super();
		this.noticeId = noticeId;
		this.empId = empId;
		this.empName = empName;
		this.noticeTitle = noticeTitle;
		this.noticeDetail = noticeDetail;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeModifyDate = noticeModifyDate;
		this.noticeDeleteDate = noticeDeleteDate;
		this.classify = classify;
		this.importance = importance;
		this.noticeReadCount = noticeReadCount;
		this.noticeOriginalFileName = noticeOriginalFileName;
		this.noticeRenameFileName = noticeRenameFileName;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getEmpid() {
		return empId;
	}

	public void setEmpid(int empid) {
		this.empId = empid;
	}

	public String getNoticeDetail() {
		return noticeDetail;
	}

	public void setNoticeDetail(String noticeDetail) {
		this.noticeDetail = noticeDetail;
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

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", empId=" + empId + ", empName=" + empName + ", noticeTitle="
				+ noticeTitle + ", noticeDetail=" + noticeDetail + ", noticeCreateDate=" + noticeCreateDate
				+ ", noticeModifyDate=" + noticeModifyDate + ", noticeDeleteDate=" + noticeDeleteDate + ", classify="
				+ classify + ", importance=" + importance + ", noticeReadCount=" + noticeReadCount
				+ ", noticeOriginalFileName=" + noticeOriginalFileName + ", noticeRenameFileName="
				+ noticeRenameFileName + "]";
	}
	
}
