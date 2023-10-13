package com.erl.pageflow.store.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("storeDao")
public class StoreDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
