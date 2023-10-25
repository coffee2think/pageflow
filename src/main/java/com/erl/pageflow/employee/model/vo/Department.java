package com.erl.pageflow.employee.model.vo;

import java.io.Serializable;

public class Department implements Serializable {

	private static final long serialVersionUID = 4651662191608981054L;
	
	private int depId;
	private String depName;
	
	public Department() {
	}

	public Department(int depId, String depName) {
		this.depId = depId;
		this.depName = depName;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Department [depId=" + depId + ", depName=" + depName + "]";
	}

}
