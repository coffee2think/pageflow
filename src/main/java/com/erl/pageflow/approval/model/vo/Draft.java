package com.erl.pageflow.approval.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Draft implements Serializable{
	
	private static final long serialVersionUID = -1817969613722544454L;
	private int apprId;
	private String draftType;
	private String detail;
	private String emergency;
	private Date startDate;
	private Date endDate;
	private String title;
	private String detailType;
	
	public Draft(int apprId, String draftType, String detail, String emergency, 
			Date startDate, Date endDate, String title, String detailType) {
		super();
		this.apprId = apprId;
		this.draftType = draftType;
		this.detail = detail;
		this.emergency = emergency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.detailType = detailType;
	}

	public int getApprId() {
		return apprId;
	}

	public void setApprId(int apprId) {
		this.apprId = apprId;
	}

	public String getDraftType() {
		return draftType;
	}

	public void setDraftType(String draftType) {
		this.draftType = draftType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	@Override
	public String toString() {
		return "Draft [apprId=" + apprId + ", draftType=" + draftType + ", detail=" + detail + ", emergency="
				+ emergency + ", startDate=" + startDate + ", endDate=" + endDate + ", title=" + title + ", detailType="
				+ detailType + "]";
	}

}
