package com.erl.pageflow.employee.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.employee.model.vo.Employee;

public interface EmployeeService {
	public Employee selectEmployee(int empId);
	ArrayList<Employee> selectEmployeeList(Paging paging);
	public int selectEmployeeListCount();
	int insertEmployee(Employee employee);
	public Employee selectEmployeeApproval(int empId);
	int updateEmployee(Employee employee);
	public int selectCheckId(int empId);
	
	// 내정보 수정
	public int myUpdateInfo(Employee employee);
}
