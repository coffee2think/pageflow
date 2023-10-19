package com.erl.pageflow.approval.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Approval implements Serializable {
	
	private static final long serialVersionUID = 8339307961689522239L;
	private int apprId;
	private int drafter;
	private int approver;
	private String draftType;
	private int lineId;
	private String apprState;
	private Date apprDate;
	private Date receiptDate;
	private Date rejectionDate;
	
	private String drafterName;
	private String approverName;
	
	private String title;
	private String detail;
	private String emergency;
	private Date startDate;
	private Date endDate;
	
	public Approval() {}
	
	public Approval(int apprId, int drafter, int approver, String draftType, int lineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.approver = approver;
		this.draftType = draftType;
		this.lineId = lineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
	}
	
	

	public Approval(int apprId, int drafter, int approver, String draftType, int lineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate, String drafterName, String approverName) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.approver = approver;
		this.draftType = draftType;
		this.lineId = lineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
		this.drafterName = drafterName;
		this.approverName = approverName;
	}

	public int getApprId() {
		return apprId;
	}

	public void setApprId(int apprId) {
		this.apprId = apprId;
	}

	public int getDrafter() {
		return drafter;
	}

	public void setDrafter(int drafter) {
		this.drafter = drafter;
	}

	public int getApprover() {
		return approver;
	}

	public void setApprover(int approver) {
		this.approver = approver;
	}

	public String getDraftType() {
		return draftType;
	}

	public void setDraftType(String draftType) {
		this.draftType = draftType;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getApprState() {
		return apprState;
	}

	public void setApprState(String apprState) {
		this.apprState = apprState;
	}

	public Date getApprDate() {
		return apprDate;
	}

	public void setApprDate(Date apprDate) {
		this.apprDate = apprDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public Date getRejectionDate() {
		return rejectionDate;
	}

	public void setRejectionDate(Date rejectionDate) {
		this.rejectionDate = rejectionDate;
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
	
	public String getDrafterName() {
		return drafterName;
	}

	public void setDrafterName(String drafterName) {
		this.drafterName = drafterName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Approval [apprId=" + apprId + ", drafter=" + drafter + ", approver=" + approver + ", draftType="
				+ draftType + ", lineId=" + lineId + ", apprState=" + apprState + ", apprDate=" + apprDate
				+ ", receiptDate=" + receiptDate + ", rejectionDate=" + rejectionDate + ", drafterName=" + drafterName
				+ ", approverName=" + approverName + ", title=" + title + ", detail=" + detail + ", emergency="
				+ emergency + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
