package com.erl.pageflow.approvalline.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ApprovalLine implements Serializable{
	
	private static final long serialVersionUID = -6283194416171534241L;
	private int lineId;
	private int empId;
	private String lineName;
	private int empId1;
	private String empName1;
	private String posName1;
	private Date stampDate1;
	
	private int empId2;
	private String empName2;
	private String posName2;
	private Date stampDate2;
	
	private int empId3;
	private String empName3;
	private String posName3;
	private Date stampDate3;
	
	private int empId4;
	private String empName4;
	private String posName4;
	private Date stampDate4;
	public ApprovalLine(int lineId, int empId, String lineName, int empId1, String empName1, String posName1,
			Date stampDate1, int empId2, String empName2, String posName2, Date stampDate2, int empId3, String empName3,
			String posName3, Date stampDate3, int empId4, String empName4, String posName4, Date stampDate4) {
		super();
		this.lineId = lineId;
		this.empId = empId;
		this.lineName = lineName;
		this.empId1 = empId1;
		this.empName1 = empName1;
		this.posName1 = posName1;
		this.stampDate1 = stampDate1;
		this.empId2 = empId2;
		this.empName2 = empName2;
		this.posName2 = posName2;
		this.stampDate2 = stampDate2;
		this.empId3 = empId3;
		this.empName3 = empName3;
		this.posName3 = posName3;
		this.stampDate3 = stampDate3;
		this.empId4 = empId4;
		this.empName4 = empName4;
		this.posName4 = posName4;
		this.stampDate4 = stampDate4;
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
	public String getEmpName1() {
		return empName1;
	}
	public void setEmpName1(String empName1) {
		this.empName1 = empName1;
	}
	public String getPosName1() {
		return posName1;
	}
	public void setPosName1(String posName1) {
		this.posName1 = posName1;
	}
	public Date getStampDate1() {
		return stampDate1;
	}
	public void setStampDate1(Date stampDate1) {
		this.stampDate1 = stampDate1;
	}
	public int getEmpId2() {
		return empId2;
	}
	public void setEmpId2(int empId2) {
		this.empId2 = empId2;
	}
	public String getEmpName2() {
		return empName2;
	}
	public void setEmpName2(String empName2) {
		this.empName2 = empName2;
	}
	public String getPosName2() {
		return posName2;
	}
	public void setPosName2(String posName2) {
		this.posName2 = posName2;
	}
	public Date getStampDate2() {
		return stampDate2;
	}
	public void setStampDate2(Date stampDate2) {
		this.stampDate2 = stampDate2;
	}
	public int getEmpId3() {
		return empId3;
	}
	public void setEmpId3(int empId3) {
		this.empId3 = empId3;
	}
	public String getEmpName3() {
		return empName3;
	}
	public void setEmpName3(String empName3) {
		this.empName3 = empName3;
	}
	public String getPosName3() {
		return posName3;
	}
	public void setPosName3(String posName3) {
		this.posName3 = posName3;
	}
	public Date getStampDate3() {
		return stampDate3;
	}
	public void setStampDate3(Date stampDate3) {
		this.stampDate3 = stampDate3;
	}
	public int getEmpId4() {
		return empId4;
	}
	public void setEmpId4(int empId4) {
		this.empId4 = empId4;
	}
	public String getEmpName4() {
		return empName4;
	}
	public void setEmpName4(String empName4) {
		this.empName4 = empName4;
	}
	public String getPosName4() {
		return posName4;
	}
	public void setPosName4(String posName4) {
		this.posName4 = posName4;
	}
	public Date getStampDate4() {
		return stampDate4;
	}
	public void setStampDate4(Date stampDate4) {
		this.stampDate4 = stampDate4;
	}
	
	@Override
	public String toString() {
		return "ApprovalLine [lineId=" + lineId + ", empId=" + empId + ", lineName=" + lineName + ", empId1=" + empId1
				+ ", empName1=" + empName1 + ", posName1=" + posName1 + ", stampDate1=" + stampDate1 + ", empId2="
				+ empId2 + ", empName2=" + empName2 + ", posName2=" + posName2 + ", stampDate2=" + stampDate2
				+ ", empId3=" + empId3 + ", empName3=" + empName3 + ", posName3=" + posName3 + ", stampDate3="
				+ stampDate3 + ", empId4=" + empId4 + ", empName4=" + empName4 + ", posName4=" + posName4
				+ ", stampDate4=" + stampDate4 + "]";
	}
	
}
