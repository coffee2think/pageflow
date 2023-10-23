package com.erl.pageflow.common;

import java.io.Serializable;

public class ApprovalKeyword implements Serializable{
	
	private static final long serialVersionUID = -793238329131741966L;
	private int apprId;
	private String draftType;
	private String apprState;
	private int startRow;
	private int endRow;
	
	public ApprovalKeyword(int apprId, String draftType) {
		super();
		this.apprId = apprId;
		this.draftType = draftType;
	}
	
	public ApprovalKeyword(int apprId, String apprState, String draftType) {
		super();
		this.apprId = apprId;
		this.apprState = apprState;
		this.draftType = draftType;
	}
	
	public ApprovalKeyword(int apprId, String draftType, int startRow, int endRow) {
		super();
		this.apprId = apprId;
		this.draftType = draftType;
		this.startRow = startRow;
		this.endRow = endRow;
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

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	public String getApprState() {
		return apprState;
	}

	public void setApprState(String apprState) {
		this.apprState = apprState;
	}

	@Override
	public String toString() {
		return "ApprovalKeyword [apprId=" + apprId + ", draftType=" + draftType + ", apprState=" + apprState
				+ ", startRow=" + startRow + ", endRow=" + endRow + "]";
	}

	

	
}
