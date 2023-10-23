package com.erl.pageflow.employee.model.vo;

import java.sql.Date;

public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = -684904099452346567L;

	private int empId;
	private String empPwd;
	private String empName;
	private Date empPwdUpdate;
	private String phone;
	private String empBirth;
	private String email;
	private String address;
	private int salary;
	private String profile;
	private Date enrollDate;
	private Date modifyDate;
	private Date leaveDate;
	
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Date lastDate;
	private int jobId;
	private int posId;
	private int depId;
	private String loginOk;
	private String adminYN;
	private String jobName;
	private String posName;
	private String depName;
	
	public Employee() {
		super();
	}
	
	

	public Employee(int empId, String empPwd, String empName, Date empPwdUpdate, String phone, String empBirth,
			String email, String address, int salary, String profile, Date enrollDate, Date modifyDate, Date leaveDate,
			Date lastDate, int jobId, int posId, int depId, String loginOk, String adminYN) {
		super();
		this.empId = empId;
		this.empPwd = empPwd;
		this.empName = empName;
		this.empPwdUpdate = empPwdUpdate;
		this.phone = phone;
		this.empBirth = empBirth;
		this.email = email;
		this.address = address;
		this.salary = salary;
		this.profile = profile;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.leaveDate = leaveDate;
		this.lastDate = lastDate;
		this.jobId = jobId;
		this.posId = posId;
		this.depId = depId;
		this.loginOk = loginOk;
		this.adminYN = adminYN;
	}
	
	public Employee(int empId, String empPwd, String empName, Date empPwdUpdate, String phone, String empBirth,
			String email, String address, int salary, String profile, Date enrollDate, Date modifyDate, Date leaveDate,
			Date lastDate, int jobId, int posId, int depId, String loginOk, String adminYN, String depName,
			String jobName, String posName) {
		super();
		this.empId = empId;
		this.empPwd = empPwd;
		this.empName = empName;
		this.empPwdUpdate = empPwdUpdate;
		this.phone = phone;
		this.empBirth = empBirth;
		this.email = email;
		this.address = address;
		this.salary = salary;
		this.profile = profile;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.leaveDate = leaveDate;
		this.lastDate = lastDate;
		this.jobId = jobId;
		this.posId = posId;
		this.depId = depId;
		this.loginOk = loginOk;
		this.adminYN = adminYN;
		this.depName = depName;
		this.jobName = jobName;
		this.posName = posName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEmpPwdUpdate() {
		return empPwdUpdate;
	}

	public void setEmpPwdUpdate(Date empPwdUpdate) {
		this.empPwdUpdate = empPwdUpdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmpBirth() {
		return empBirth;
	}

	public void setEmpBirth(String empBirth) {
		this.empBirth = empBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getPosId() {
		return posId;
	}

	public void setPosId(int posId) {
		this.posId = posId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getLoginOk() {
		return loginOk;
	}

	public void setLoginOk(String loginOk) {
		this.loginOk = loginOk;
	}

	public String getAdminYN() {
		return adminYN;
	}

	public void setAdminYN(String adminYN) {
		this.adminYN = adminYN;
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

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empPwd=" + empPwd + ", empName=" + empName + ", empPwdUpdate="
				+ empPwdUpdate + ", phone=" + phone + ", empBirth=" + empBirth + ", email=" + email + ", address="
				+ address + ", salary=" + salary + ", profile=" + profile 
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + ", leaveDate=" + leaveDate
				+ ", lastDate=" + lastDate + ", jobId=" + jobId + ", posId=" + posId + ", depId=" + depId + ", loginOk="
				+ loginOk + ", adminYN=" + adminYN + ", jobName=" + jobName + ", posName=" + posName + ", depName="
				+ depName + "]";
	}

	

}
