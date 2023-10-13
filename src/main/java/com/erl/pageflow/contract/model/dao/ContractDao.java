package com.erl.pageflow.contract.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("contractDao")
public class ContractDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
