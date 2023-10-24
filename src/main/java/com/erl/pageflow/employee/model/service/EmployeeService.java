package com.erl.pageflow.employee.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.vo.Employee;

public interface EmployeeService {
	public Employee selectEmployee(int empId);
	ArrayList<Employee> selectEmployeeList(Paging paging);
	public int selectEmployeeListCount();
	int insertEmployee(Employee employee);
	public Employee selectEmployeeApproval(int empId);
	int updateEmployee(Employee employee);
	public int selectCheckId(int empid);
	int selectSearchEmpCount(String keyword);
    int selectSearchDeptCount(String keyword);
	public ArrayList<Employee> selectSearchEmp(Search search);
	public ArrayList<Employee> selectSearchDept(Search search);
	
	
}
