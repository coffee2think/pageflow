package com.erl.pageflow.employee.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
