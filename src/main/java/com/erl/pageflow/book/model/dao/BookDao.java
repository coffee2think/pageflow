package com.erl.pageflow.book.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
}
