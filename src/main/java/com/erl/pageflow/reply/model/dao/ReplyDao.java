package com.erl.pageflow.reply.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erl.pageflow.common.ReplyKeyword;
import com.erl.pageflow.reply.model.vo.Reply;

@Repository("replyDao")
public class ReplyDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//업무게시판 게시글 리스트 조회
	public ArrayList<Reply> selectReplyList(ReplyKeyword replyKeyword) {
		List<Reply> list = sqlSessionTemplate.selectList("replyMapper.selectReplyList", replyKeyword);
		return (ArrayList<Reply>)list;
	}
	
	public int selectReplyListCount(ReplyKeyword replyKeyword) {
		return sqlSessionTemplate.selectOne("replyMapper.selectReplyListCount", replyKeyword);
	}

	public String selectReplyEmpName(int replyId) {
		return sqlSessionTemplate.selectOne("replyMapper.selectReplyEmpName", replyId);
	}
	
	
	
}
