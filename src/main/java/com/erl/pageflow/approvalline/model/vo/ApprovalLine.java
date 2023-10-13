package com.erl.pageflow.approvalline.model.vo;

import java.io.Serializable;

public class ApprovalLine implements Serializable {
	
	private static final long serialVersionUID = -4827851453826784289L;
	private int lineId;
	private int empId;
	private String lineName;
	private int empId1;
	private int empId2;
	private int empId3;
	private int empId4;
	
	public ApprovalLine() {}

	public ApprovalLine(int lineId, int empId, String lineName, int empId1, int empId2, int empId3, int empId4) {
		super();
		this.lineId = lineId;
		this.empId = empId;
		this.lineName = lineName;
		this.empId1 = empId1;
		this.empId2 = empId2;
		this.empId3 = empId3;
		this.empId4 = empId4;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public int getEmpId1() {
		return empId1;
	}

	public void setEmpId1(int empId1) {
		this.empId1 = empId1;
	}

	public int getEmpId2() {
		return empId2;
	}

	public void setEmpId2(int empId2) {
		this.empId2 = empId2;
	}

	public int getEmpId3() {
		return empId3;
	}

	public void setEmpId3(int empId3) {
		this.empId3 = empId3;
	}

	public int getEmpId4() {
		return empId4;
	}

	public void setEmpId4(int empId4) {
		this.empId4 = empId4;
	}

	@Override
	public String toString() {
		return "ApprovalLine [lineId=" + lineId + ", empId=" + empId + ", lineName=" + lineName + ", empId1=" + empId1
				+ ", empId2=" + empId2 + ", empId3=" + empId3 + ", empId4=" + empId4 + "]";
	}
	
}
