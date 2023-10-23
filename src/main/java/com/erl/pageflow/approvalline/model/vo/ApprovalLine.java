package com.erl.pageflow.approvalline.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ApprovalLine implements Serializable{
	
	private static final long serialVersionUID = -6283194416171534241L;
	private int lineId;
	private int lineDepth;
	private int empId;
	private int approverId;
	private String approverName;
	private String posName;
	private String stampCheck;
	private Date stampDate;
	
	public ApprovalLine(int lineId, int lineDepth, int empId, int approverId, String approverName, String posName,
			String stampCheck, Date stampDate) {
		super();
		this.lineId = lineId;
		this.lineDepth = lineDepth;
		this.empId = empId;
		this.approverId = approverId;
		this.approverName = approverName;
		this.posName = posName;
		this.stampCheck = stampCheck;
		this.stampDate = stampDate;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
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

	public String getStampCheck() {
		return stampCheck;
	}

	public void setStampCheck(String stampCheck) {
		this.stampCheck = stampCheck;
	}

	public Date getStampDate() {
		return stampDate;
	}
	
	public void setStampDate(Date stampDate) {
		this.stampDate = stampDate;
	}
	
	@Override
	public String toString() {
		return "ApprovalLine [lineId=" + lineId + ", lineDepth=" + lineDepth + ", empId=" + empId + ", approverId="
				+ approverId + ", approverName=" + approverName + ", posName=" + posName + ", stampCheck=" + stampCheck
				+ ", stampDate=" + stampDate + "]";
	}
	
	
}
