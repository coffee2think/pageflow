package com.erl.pageflow.refund.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.Paging;
import com.erl.pageflow.refund.model.vo.Refund;

@Repository("refundDao")
public class RefundDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int selectGetListCount() {
		return sqlSessionTemplate.selectOne("refundMapper.selectGetListCount");
	}

	public ArrayList<Refund> selectRefundList(Paging paging) {
		List<Refund> list = sqlSessionTemplate.selectList("refundMapper.selectRefundList", paging);
		return (ArrayList<Refund>) list;
	}
}
