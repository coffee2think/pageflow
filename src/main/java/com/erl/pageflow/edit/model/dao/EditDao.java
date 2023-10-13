package com.erl.pageflow.edit.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("editDao")
public class EditDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
