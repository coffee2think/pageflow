package com.erl.pageflow.sales.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("salesDao")
public class SalesDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
