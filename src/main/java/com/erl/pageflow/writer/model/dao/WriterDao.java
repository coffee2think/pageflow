package com.erl.pageflow.writer.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("writerDao")
public class WriterDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
