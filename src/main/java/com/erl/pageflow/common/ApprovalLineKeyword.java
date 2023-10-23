package com.erl.pageflow.common;

import java.io.Serializable;

public class ApprovalLineKeyword implements Serializable{
	
	private static final long serialVersionUID = -1050328831292885628L;
	private int apprId;
	private int lineId;
	private int approverId;
	private String stampCheck;
	
	public ApprovalLineKeyword() {}

	public ApprovalLineKeyword(int lineId, int approverId, String stampCheck) {
		super();
		this.lineId = lineId;
		this.approverId = approverId;
		this.stampCheck = stampCheck;
	}
	
	public ApprovalLineKeyword(int apprId, int lineId, int approverId, String stampCheck) {
		super();
		this.apprId = apprId;
		this.lineId = lineId;
		this.approverId = approverId;
		this.stampCheck = stampCheck;
	}

	public int getApprId() {
		return apprId;
	}

	public void setApprId(int apprId) {
		this.apprId = apprId;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public String getStampCheck() {
		return stampCheck;
	}

	public void setStampCheck(String stampCheck) {
		this.stampCheck = stampCheck;
	}

	@Override
	public String toString() {
		return "ApprovalLineKeyword [apprId=" + apprId + ", lineId=" + lineId + ", approverId=" + approverId
				+ ", stampCheck=" + stampCheck + "]";
	}
	
	
	
}
