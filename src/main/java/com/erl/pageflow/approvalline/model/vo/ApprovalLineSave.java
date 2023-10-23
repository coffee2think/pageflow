package com.erl.pageflow.approvalline.model.vo;

import java.io.Serializable;

public class ApprovalLineSave implements Serializable{
	
	private static final long serialVersionUID = -4238424095950104706L;
	private int savelineId;
	private int lineDepth;
	private int empId;
	private int approverId;
	private String approverName;
	private String posName;
	private String lineName;
	
	public ApprovalLineSave(int savelineId, int lineDepth, int empId, int approverId, 
			String approverName, String posName, String lineName) {
		super();
		this.savelineId = savelineId;
		this.lineDepth = lineDepth;
		this.empId = empId;
		this.approverId = approverId;
		this.approverName = approverName;
		this.posName = posName;
		this.lineName = lineName;
	}

	public int getSavelineId() {
		return savelineId;
	}

	public void setSavelineId(int savelineId) {
		this.savelineId = savelineId;
	}

	public int getLineDepth() {
		return lineDepth;
	}

	public void setLineDepth(int lineDepth) {
		this.lineDepth = lineDepth;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Override
	public String toString() {
		return "ApprovalLineSave [savelineId=" + savelineId + ", lineDepth=" + lineDepth + ", empId=" + empId
				+ ", approverId=" + approverId + ", approverName=" + approverName + ", posName=" + posName
				+ ", lineName=" + lineName + "]";
	}
	
}
