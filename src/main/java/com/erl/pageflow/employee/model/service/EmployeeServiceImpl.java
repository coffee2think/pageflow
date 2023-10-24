package com.erl.pageflow.employee.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.dao.EmployeeDao;
import com.erl.pageflow.employee.model.vo.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee selectEmployee(int empId) {
		return employeeDao.selectEmployee(empId);
	}

	@Override
	public ArrayList<Employee> selectEmployeeList(Paging paging) {
		
		return employeeDao.selectEmployeeList(paging);
	}

	@Override
	public int selectEmployeeListCount() {
		
		return employeeDao.selectEmployeeListCount();
	}

	@Override
	public int insertEmployee(Employee employee) {
		
		return employeeDao.insertEmployee(employee);
	}

	@Override
	public Employee selectEmployeeApproval(int empId) {
		return employeeDao.selectEmployeeApproval(empId);
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	@Override
	public int selectCheckId(int empid) {
		
		return employeeDao.selectCheckId(empid);
	}

	@Override
	public int selectSearchEmpCount(String keyword) {
		
		return employeeDao.selectSearchEmpCount(keyword);
	}
	
	@Override
	public int selectSearchDeptCount(String keyword) {
		
		return employeeDao.selectSearchDeptCount(keyword);
	}

	@Override
	public ArrayList<Employee> selectSearchEmp(Search search) {
		
		return employeeDao.selectSearchEmp(search);
	}

	@Override
	public ArrayList<Employee> selectSearchDept(Search search) {
		
		return employeeDao.selectSearchDept(search);
	}

	@Override
	public int myUpdateInfo(Employee employee) {
		return employeeDao.myUpdateInfo(employee);
	}
	
	@Override
	public int myUpdateInfo2(Employee employee) {
		return employeeDao.myUpdateInfo2(employee);
	}
}
