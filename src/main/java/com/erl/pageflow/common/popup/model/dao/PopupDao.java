package com.erl.pageflow.common.popup.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("popupDao")
public class PopupDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
}
