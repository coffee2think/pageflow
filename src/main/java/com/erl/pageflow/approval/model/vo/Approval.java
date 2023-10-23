package com.erl.pageflow.approval.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Approval implements Serializable {
	
	private static final long serialVersionUID = 8339307961689522239L;
	private int apprId;
	private int drafter;
	private String draftType;
	private int lineId;
	private int savelineId;
	private String apprState;
	private Date apprDate;
	private Date receiptDate;
	private Date rejectionDate;
	
	private String detailType;
	
	private String originFile;
	private String renameFile;
	
	private String drafterName;
	private String approverName;
	
	private String depName;
	private String jobName;
	private String posName;
	
	private String title;
	private String detail;
	private String emergency;
	private Date startDate;
	private Date endDate;
	
	public Approval() {}
	
	public Approval(int apprId, int drafter, String draftType, int lineId, int savelineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.draftType = draftType;
		this.lineId = lineId;
		this.savelineId = savelineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
	}
	
	public Approval(int apprId, int drafter, String draftType, int lineId, int savelineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate, String originFile, String renameFile) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.draftType = draftType;
		this.lineId = lineId;
		this.savelineId = savelineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
		this.originFile = originFile;
		this.renameFile = renameFile;
	}

	public Approval(int apprId, int drafter, String draftType, int lineId, int savelineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate, String drafterName, String approverName,
			String originFile, String renameFile,
			String depName, String jobName, String posName) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.draftType = draftType;
		this.lineId = lineId;
		this.savelineId = savelineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
		this.drafterName = drafterName;
		this.approverName = approverName;
		
		this.originFile = originFile;
		this.renameFile = renameFile;
		
		this.depName = depName;
		this.jobName = jobName;
		this.posName = posName;
	}
	
	public Approval(int apprId, int drafter, String draftType, int lineId, int savelineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate, String drafterName, String approverName, String originFile, String renameFile) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.draftType = draftType;
		this.lineId = lineId;
		this.savelineId = savelineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
		this.drafterName = drafterName;
		this.approverName = approverName;
		this.originFile = originFile;
		this.renameFile = renameFile;
	}
	
	public Approval(int apprId, int drafter, String draftType, int lineId, int savelineId, String apprState,
			Date apprDate, Date receiptDate, Date rejectionDate, String drafterName, String approverName, 
			String originFile, String renameFile, String detailType) {
		super();
		this.apprId = apprId;
		this.drafter = drafter;
		this.draftType = draftType;
		this.lineId = lineId;
		this.savelineId = savelineId;
		this.apprState = apprState;
		this.apprDate = apprDate;
		this.receiptDate = receiptDate;
		this.rejectionDate = rejectionDate;
		this.drafterName = drafterName;
		this.approverName = approverName;
		this.originFile = originFile;
		this.renameFile = renameFile;
		this.detailType = detailType;
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
	
	
	
	public int getSavelineId() {
		return savelineId;
	}

	public void setSavelineId(int savelineId) {
		this.savelineId = savelineId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}
	
	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	

	public String getOriginFile() {
		return originFile;
	}

	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}

	public String getRenameFile() {
		return renameFile;
	}

	public void setRenameFile(String renameFile) {
		this.renameFile = renameFile;
	}

	@Override
	public String toString() {
		return "Approval [apprId=" + apprId + ", drafter=" + drafter + ", draftType=" + draftType + ", lineId=" + lineId
				+ ", savelineId=" + savelineId + ", apprState=" + apprState + ", apprDate=" + apprDate
				+ ", receiptDate=" + receiptDate + ", rejectionDate=" + rejectionDate + ", detailType=" + detailType
				+ ", originFile=" + originFile + ", renameFile=" + renameFile + ", drafterName=" + drafterName
				+ ", approverName=" + approverName + ", depName=" + depName + ", jobName=" + jobName + ", posName="
				+ posName + ", title=" + title + ", detail=" + detail + ", emergency=" + emergency + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}

	
}
