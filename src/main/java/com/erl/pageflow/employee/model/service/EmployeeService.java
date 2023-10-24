package com.erl.pageflow.employee.model.service;

import java.util.ArrayList;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.vo.Employee;
import com.erl.pageflow.employee.model.vo.SearchEmp;

public interface EmployeeService {
	public Employee selectEmployee(int empId);
	ArrayList<Employee> selectEmployeeList(Paging paging);
	public int selectEmployeeListCount();
	int insertEmployee(Employee employee);
	public Employee selectEmployeeApproval(int empId);
	int updateEmployee(Employee employee);
	public int selectCheckId(int empid);
	int selectSearchEmpCount(String keyword);
	public ArrayList<Employee> selectSearchEmp(Search search);
	public int updateLoginOK(Employee employee);
	public int updateAdminYN(Employee employee);
	public int updateEmpLeave(int empId);
	public int selectSearchPosCount(String posName);
	public int selectSearchDeptPosCount(Employee employee);
	int selectSearchDeptCount(String deptName);
	public ArrayList<Employee> selectSearchDept(Search search);
	public ArrayList<Employee> selectSearchDeptPos(SearchEmp searchEmp);
	public ArrayList<Employee> selectSearchPos(Search search);
	
	
}
