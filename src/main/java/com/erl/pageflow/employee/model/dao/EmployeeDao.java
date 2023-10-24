package com.erl.pageflow.employee.model.dao;

import java.util.ArrayList;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.common.Search;
import com.erl.pageflow.employee.model.vo.Employee;

@Repository("employeeDao")
public class EmployeeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public Employee selectEmployee(int empId) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectEmployee", empId);
	}
	
	public int selectEmployeeListCount() {
		return sqlSessionTemplate.selectOne("employeeMapper.selectListCount");
	}
	
	public ArrayList<Employee> selectEmployeeList(Paging paging) {
		List<Employee> list = sqlSessionTemplate.selectList("employeeMapper.selectEmployeeList", paging);
		return (ArrayList<Employee>)list;
	}
	
	public int insertEmployee(Employee employee) {
		return sqlSessionTemplate.insert("employeeMapper.insertEmployee", employee);
	}

	public Employee selectEmployeeApproval(int empId) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectEmployeeApproval", empId);
	}
	
	public int updateEmployee(Employee employee) {
		return sqlSessionTemplate.update("employeeMapper.updateEmployee", employee);
	}
	
	public int selectCheckId( int empid) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectCheckId", empid);
	}

	public ArrayList<Employee> selectSearchDept(Search search) {
		List<Employee> list = sqlSessionTemplate.selectList("employeeMapper.selectSearchDept", search);
		return (ArrayList<Employee>)list;
	}

	public ArrayList<Employee> selectSearchEmp(Search search) {
		List<Employee> list = sqlSessionTemplate.selectList("employeeMapper.selectSearchEmp", search);
		return (ArrayList<Employee>)list;
	}

	public int selectSearchDeptCount(String keyword) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectSearchDeptCount", keyword);
	}

	public int selectSearchEmpCount(String keyword) {
		return sqlSessionTemplate.selectOne("employeeMapper.selectSearchEmpCount", keyword);
	}
	
	
}
