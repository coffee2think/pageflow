package com.erl.pageflow.employee.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.employee.model.vo.Employee;

@Repository("employeeDao")
public class EmployeeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Employee selectEmployee(int empId) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectEmployee", empId);
	}
}
